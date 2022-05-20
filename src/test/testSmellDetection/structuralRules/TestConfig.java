package testSmellDetection.structuralRules;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;

public class TestConfig extends LightJavaCodeInsightFixtureTestCase {

    private String fileName = "";

    public void setFileName(String fileName){
        this.fileName = fileName;
        myFixture.configureByFile("test/"+fileName);
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/testdata/";
    }
}
