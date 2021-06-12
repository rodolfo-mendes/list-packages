/*
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <https://unlicense.org>
 */
package listpackages;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.stream;

public class ListPackages {
    private final PrintStream out;

    public ListPackages(PrintStream out) {
        this.out = out;
    }

    public void listPackages(File javaDir) {
        checkNotNull(javaDir, "javaDir cannot be null.");
        checkArgument(javaDir.exists(), "Directory %s does not exist.", javaDir.getAbsolutePath());
        checkArgument(javaDir.isDirectory(), "Directory %s is not a directory.");

        File[] packages = javaDir.listFiles(file -> (file != null && file.exists() && file.isDirectory()));
        stream(packages).forEach(pack -> scanPackage(pack, null));
    }

    private void scanPackage(File pack, String parentPackage) {
        checkNotNull(pack, "pack cannot be null.");
        checkArgument(pack.exists(), "Directory %s does not exist.", pack.getAbsolutePath());
        checkArgument(pack.isDirectory(), "Directory %s is not a directory.");

        final String fullPackName = Joiner.on(".").skipNulls().join(parentPackage,pack.getName());
        out.println(fullPackName);

        File[] packages = pack.listFiles(file -> (file != null && file.exists() && file.isDirectory()));
        stream(packages).forEach(childPack -> scanPackage(childPack, fullPackName));
    }
}
