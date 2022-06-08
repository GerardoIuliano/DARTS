package windowCommitConstruction.testSmellPanel;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import testSmellDetection.testSmellInfo.DuplicateAssert.DuplicateAssertInfo;
import testSmellDetection.testSmellInfo.DuplicateAssert.MethodWithDuplicateAssert;
import testSmellDetection.testSmellInfo.magicNamberTest.MagicNumberTestInfo;
import testSmellDetection.testSmellInfo.magicNamberTest.MethodWithMagicNumber;
import windowCommitConstruction.DuplicateAssertCP;
import windowCommitConstruction.MagicNumberCP;
import windowCommitConstruction.general.RefactorWindow;
import windowCommitConstruction.general.listRenderer.CustomListRenderer2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class DASmellPanel extends JSplitPane implements ListSelectionListener {

    private JBList smellList;
    private JBPanel refactorPreviewPanel;

    ArrayList<String> methodsNames = new ArrayList<>();

    private DuplicateAssertInfo duplicateAssertInfo;
    private Project project;
    private DuplicateAssertCP duplicateAssertCP;
    DefaultListModel model;

    Dimension minimumSize = new Dimension(150, 100);

    public DASmellPanel(DuplicateAssertInfo duplicateAssertInfo, Project project, DuplicateAssertCP duplicateAssertCP){
        this.project = project;
        this.duplicateAssertCP = duplicateAssertCP;

        model = new DefaultListModel ();

        this.refactorPreviewPanel = new JBPanel();
        this.duplicateAssertInfo = duplicateAssertInfo;

        for(MethodWithDuplicateAssert methodWithDuplicateAssert : duplicateAssertInfo.getMethodsThatCauseDuplicateAssert()){
            model.addElement(methodWithDuplicateAssert.getMethodWithDuplicateAssert().getName());
            methodsNames.add(methodWithDuplicateAssert.getMethodWithDuplicateAssert().getName());
        }

        smellList = new JBList(model);
        smellList.setCellRenderer( new CustomListRenderer2(smellList));

        smellList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        smellList.setSelectedIndex(0);
        smellList.addListSelectionListener(this);
        JBScrollPane smellScrollPane = new JBScrollPane(smellList);
        smellScrollPane.setBorder(new TitledBorder("METHODS"));

        // Creazione dello split pane con la lista degli smell e la preview del refactoring.
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setLeftComponent(smellScrollPane);
        this.setOneTouchExpandable(true);
        this.setDividerLocation(150);

        // Fornisco le dimensioni minime dei due panel e una dimensione di base per l'intero panel.
        smellScrollPane.setMinimumSize(minimumSize);
        refactorPreviewPanel.setMinimumSize(minimumSize);
        this.setPreferredSize(new Dimension(400, 200));

        // Starto il secondo panel per la prima volta.
        updateRefactorPreviewLabel(smellList.getSelectedIndex());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        updateRefactorPreviewLabel(list.getSelectedIndex());
    }

    //Renders the selected image
    protected void updateRefactorPreviewLabel (int index) {
        MethodWithDuplicateAssert methodWithDA;
        if(index == -1 && model.getSize() == 0){
            return;
        } else if(index == -1){
            methodWithDA = duplicateAssertInfo.getMethodsThatCauseDuplicateAssert().get(0);
        } else {
            methodWithDA = duplicateAssertInfo.getMethodsThatCauseDuplicateAssert().get(index);
        }
        RefactorWindow refactorWindow = new RefactorWindow(methodWithDA, duplicateAssertInfo, project, this);
        this.setRightComponent(refactorWindow.getRootPanel());
    }
}

