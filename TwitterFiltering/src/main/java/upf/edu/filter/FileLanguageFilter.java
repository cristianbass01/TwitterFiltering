package upf.edu.filter;

public class FileLanguageFilter implements LanguageFilter{
    final String inputFile;
    final String outputFile;

    public FileLanguageFilter(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void filterLanguage(String language) throws Exception {

    }
}
