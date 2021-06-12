# List Packages

A simple command-line tool to list the packages in your Java project.

## Building

Clone the project repository:

```
git@github.com:rodolfo-mendes/list-packages.git
```

Change to the project directory, compile the project then assembly it to build the .jar:

```
cd list-packages
mvn clean install
mvn assembly:assembly
```

## Usage

Run the command under the root directory of your packages:

```
java -jar list-packages.jar
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

# License

[Unlicense](https://choosealicense.com/licenses/unlicense)