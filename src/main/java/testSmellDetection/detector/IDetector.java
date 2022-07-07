package testSmellDetection.detector;

import testSmellDetection.testSmellInfo.DuplicateAssert.DuplicateAssertInfo;
import testSmellDetection.testSmellInfo.ExceptionHandlingInfo.ExceptionHandlingInfo;
import testSmellDetection.testSmellInfo.IgnoredTest.IgnoredTestInfo;
import testSmellDetection.testSmellInfo.conditionalTestLogic.CondTestLogicInfo;
import testSmellDetection.testSmellInfo.constructorInitialization.ConstructorInitializationInfo;
import testSmellDetection.testSmellInfo.eagerTest.EagerTestInfo;
import testSmellDetection.testSmellInfo.generalFixture.GeneralFixtureInfo;
import testSmellDetection.testSmellInfo.lackOfCohesion.LackOfCohesionInfo;
import testSmellDetection.testSmellInfo.magicNamberTest.MagicNumberTestInfo;

import java.util.ArrayList;

public interface IDetector {
    ArrayList<GeneralFixtureInfo> executeDetectionForGeneralFixture();

    ArrayList<EagerTestInfo> executeDetectionForEagerTest();

    ArrayList<LackOfCohesionInfo> executeDetectionForLackOfCohesion();

    ArrayList<MagicNumberTestInfo> executeDetectionForMagicNumber();

    ArrayList<ConstructorInitializationInfo> executeDetectionForConstructorInitialization();

    ArrayList<ExceptionHandlingInfo> executeDetectionForExceptionHandling(int threshold);

    ArrayList<CondTestLogicInfo> executeDetectionForCondTestLogic(int threshold);

    ArrayList<DuplicateAssertInfo> executeDetectionForDuplicateAssertInfo();

    ArrayList<IgnoredTestInfo> executeDetectionForIgnoredTestInfo();

}
