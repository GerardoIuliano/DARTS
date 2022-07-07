package testSmellDetection.testSmellInfo.IgnoredTest;

import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.testSmellInfo.TestSmellInfo;

import java.util.ArrayList;

public class IgnoredTestInfo extends TestSmellInfo {


    private static final PsiClassBean classWithIgnoredTest = null;
    private ArrayList<MethodWithIgnoredTest> methodsThatIgnoredTest;

    public IgnoredTestInfo(PsiClassBean classWithIgnoredTest, ArrayList<MethodWithIgnoredTest> methodsThatCauseIgnoredTest) {
        super(classWithIgnoredTest);
        this.methodsThatIgnoredTest = methodsThatCauseIgnoredTest;
    }

    @Override
    public String toString() {
        return "IgnoredTestInfo{" +
                "classWithIgnoredTest=" + classWithSmell +
                ", methodsThatCauseLackOfCohesion=" + methodsThatIgnoredTest+
                '}';
    }

    public static PsiClassBean getClassWithIgnoredTest() {
        return classWithIgnoredTest;
    }

    public ArrayList<MethodWithIgnoredTest> getMethodsThatIgnoredTest() {
        return methodsThatIgnoredTest;
    }

    public void setMethodsThatIgnoredTest(ArrayList<MethodWithIgnoredTest> methodsThatIgnoredTest) {
        this.methodsThatIgnoredTest = methodsThatIgnoredTest;
    }
}
