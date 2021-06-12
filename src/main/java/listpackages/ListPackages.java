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
