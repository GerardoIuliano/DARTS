package testSmellDetection.testSmellInfo.IgnoredTest;

import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.testSmellInfo.TestSmellInfo;

import java.util.ArrayList;

public class IgnoredTestInfo extends TestSmellInfo {


    private static final PsiClassBean classWithIgnoredTest = null;
    private PsiClassBean productionClass;
    private ArrayList<MethodWithIgnoredTest> methodsThatIgnoredTest;

    public IgnoredTestInfo(PsiClassBean classWithIgnoredTest, PsiClassBean productionClass, ArrayList<MethodWithIgnoredTest> methodsThatCauseIgnoredTest) {
        super(classWithIgnoredTest);
        this.productionClass = productionClass;
        this.methodsThatIgnoredTest = methodsThatCauseIgnoredTest;
    }

    @Override
    public String toString() {
        return "IgnoredTestInfo{" +
                "classWithIgnoredTest=" + classWithSmell +
                ", productionClass=" + productionClass +
                ", methodsThatCauseLackOfCohesion=" + methodsThatIgnoredTest+
                '}';
    }

    public static PsiClassBean getClassWithIgnoredTest() {
        return classWithIgnoredTest;
    }

    public PsiClassBean getProductionClass() {
        return productionClass;
    }

    public void setProductionClass(PsiClassBean productionClass) {
        this.productionClass = productionClass;
    }

    public ArrayList<MethodWithIgnoredTest> getMethodsThatIgnoredTest() {
        return methodsThatIgnoredTest;
    }

    public void setMethodsThatIgnoredTest(ArrayList<MethodWithIgnoredTest> methodsThatIgnoredTest) {
        this.methodsThatIgnoredTest = methodsThatIgnoredTest;
    }
}
