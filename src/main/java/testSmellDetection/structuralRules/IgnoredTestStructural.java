package testSmellDetection.structuralRules;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifierList;
import org.jetbrains.annotations.NotNull;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.testSmellInfo.IgnoredTest.MethodWithIgnoredTest;
import testSmellDetection.testSmellInfo.magicNamberTest.MethodWithMagicNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class IgnoredTestStructural {

    public static ArrayList<MethodWithIgnoredTest> checkMethodsThatCauseIgnoredTest(PsiClassBean testClass) {
        ArrayList<MethodWithIgnoredTest> methodWithIgnoredTests = new ArrayList<>();
        for(PsiMethodBean psiMethodBeanInside : testClass.getPsiMethodBeans()){
            String methodName = psiMethodBeanInside.getPsiMethod().getName();
            if(!methodName.equals(testClass.getPsiClass().getName()) &&
                    !methodName.toLowerCase().equals("setup") &&
                    !methodName.toLowerCase().equals("teardown")) {

                PsiModifierList psiModifierList = psiMethodBeanInside.getPsiMethod().getModifierList();
                PsiAnnotation[] annotations = psiModifierList.getAnnotations();

                for(int i=0; i< annotations.length; i++){
                    if (annotations[i].getText().equals("@Ignore")){
                        methodWithIgnoredTests.add(new MethodWithIgnoredTest(psiMethodBeanInside));
                    }
                }

            }
        }
        if(methodWithIgnoredTests.isEmpty()){
            return null;
        } else {
            return methodWithIgnoredTests;
        }
    }
}
