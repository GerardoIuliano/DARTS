package testSmellDetection.structuralRules;

import com.intellij.psi.PsiMethodCallExpression;
import testSmellDetection.bean.PsiClassBean;
import testSmellDetection.bean.PsiMethodBean;
import testSmellDetection.testSmellInfo.DuplicateAssert.MethodWithDuplicateAssert;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class DuplicateAssertStructural {

    public static ArrayList<MethodWithDuplicateAssert> checkMethodsThatCauseDuplicateAssert(PsiClassBean testClass) {
        ArrayList<MethodWithDuplicateAssert> methodsWithDuplicateAsserts = new ArrayList<>();
        int counter = 0;
        HashSet set = new HashSet();
        ArrayList<String> names = new ArrayList<>();
        for(PsiMethodBean psiMethodBeanInside : testClass.getPsiMethodBeans()){
            String methodName = psiMethodBeanInside.getPsiMethod().getName();
            if(!methodName.equals(testClass.getPsiClass().getName()) &&
                    !methodName.toLowerCase().equals("setup") &&
                    !methodName.toLowerCase().equals("teardown")){
                ArrayList<PsiMethodCallExpression> methodCalls = psiMethodBeanInside.getMethodCalls();
                if(methodCalls.size() >= 1){
                    for(PsiMethodCallExpression callExpression : methodCalls){
                        /*Prendo il nome del metodo */
                        String methodCallName = callExpression.getMethodExpression().getQualifiedName();
                        if(methodCallName.toLowerCase().contains("assert")){
                            String firtsArg = callExpression.getArgumentList().getExpressions()[0].getText();
                            names.add(firtsArg);
                            counter++;
                        }
                    }
                    if (counter>1){
                        for (String name : names) {
                            if (!set.add(name)) {
                                methodsWithDuplicateAsserts.add(new MethodWithDuplicateAssert(psiMethodBeanInside));
                            }
                        }
                    }
                }
            }
            set.clear();
            names.clear();
            counter=0;
        }
        if (methodsWithDuplicateAsserts.isEmpty()) {
            return null;
        } else {
            return methodsWithDuplicateAsserts;
        }
    }

}