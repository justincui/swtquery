package kr.or.eclipse.swt.query.util;

import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.IME;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tracker;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.FilteredList;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.AbstractHyperlink;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.ScrolledFormText;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.SharedScrolledComposite;
import org.eclipse.ui.forms.widgets.ToggleHyperlink;
import org.eclipse.ui.forms.widgets.TreeNode;
import org.eclipse.ui.forms.widgets.Twistie;
import org.eclipse.ui.part.DrillDownComposite;
import org.eclipse.ui.part.PageBook;

public class WidgetSwitch<T> {
    public T caseTray(Tray widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseTracker(Tracker widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseToolTip(ToolTip widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseScrollBar(ScrollBar widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseMenu(Menu widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseCTabItem(CTabItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTreeItem(TreeItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTreeColumn(TreeColumn widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTrayItem(TrayItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseToolItem(ToolItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTableItem(TableItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTableColumn(TableColumn widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseTabItem(TabItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseMenuItem(MenuItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseExpandItem(ExpandItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseCoolItem(CoolItem widget, Object... args) {
        return caseItem((Item) widget, args);
    }

    public T caseItem(Item widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseIME(IME widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseSlider(Slider widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseText(Text widget, Object... args) {
        return caseScrollable((Scrollable) widget, args);
    }

    public T caseList(List widget, Object... args) {
        return caseScrollable((Scrollable) widget, args);
    }

    public T caseForm(Form widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T casePageBook(PageBook widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseDrillDownComposite(DrillDownComposite widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseFilteredTree(FilteredTree widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseFilteredList(FilteredList widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseProgressMonitorPart(ProgressMonitorPart widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseProgressIndicator(ProgressIndicator widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseBrowser(Browser widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseOleFrame(OleFrame widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseOleControlSite(OleControlSite widget, Object... args) {
        return caseOleClientSite((OleClientSite) widget, args);
    }

    public T caseOleClientSite(OleClientSite widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseViewForm(ViewForm widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseScrolledPageBook(ScrolledPageBook widget, Object... args) {
        return caseSharedScrolledComposite((SharedScrolledComposite) widget, args);
    }

    public T caseScrolledFormText(ScrolledFormText widget, Object... args) {
        return caseSharedScrolledComposite((SharedScrolledComposite) widget, args);
    }

    public T caseScrolledForm(ScrolledForm widget, Object... args) {
        return caseSharedScrolledComposite((SharedScrolledComposite) widget, args);
    }

    public T caseSharedScrolledComposite(SharedScrolledComposite widget, Object... args) {
        return caseScrolledComposite((ScrolledComposite) widget, args);
    }

    public T caseScrolledComposite(ScrolledComposite widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseSashForm(SashForm widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseCTabFolder(CTabFolder widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseCCombo(CCombo widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseCBanner(CBanner widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseTree(Tree widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseToolBar(ToolBar widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseTable(Table widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseTabFolder(TabFolder widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseSpinner(Spinner widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseGroup(Group widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseExpandBar(ExpandBar widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseDateTime(DateTime widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseCoolBar(CoolBar widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseCombo(Combo widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseFormText(FormText widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseSection(Section widget, Object... args) {
        return caseExpandableComposite((ExpandableComposite) widget, args);
    }

    public T caseExpandableComposite(ExpandableComposite widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseTwistie(Twistie widget, Object... args) {
        return caseToggleHyperlink((ToggleHyperlink) widget, args);
    }

    public T caseTreeNode(TreeNode widget, Object... args) {
        return caseToggleHyperlink((ToggleHyperlink) widget, args);
    }

    public T caseToggleHyperlink(ToggleHyperlink widget, Object... args) {
        return caseAbstractHyperlink((AbstractHyperlink) widget, args);
    }

    public T caseImageHyperlink(ImageHyperlink widget, Object... args) {
        return caseHyperlink((Hyperlink) widget, args);
    }

    public T caseHyperlink(Hyperlink widget, Object... args) {
        return caseAbstractHyperlink((AbstractHyperlink) widget, args);
    }

    public T caseAbstractHyperlink(AbstractHyperlink widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseGLCanvas(GLCanvas widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseTableCursor(TableCursor widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseStyledText(StyledText widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseCLabel(CLabel widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseShell(Shell widget, Object... args) {
        return caseDecorations((Decorations) widget, args);
    }

    public T caseDecorations(Decorations widget, Object... args) {
        return caseCanvas((Canvas) widget, args);
    }

    public T caseCanvas(Canvas widget, Object... args) {
        return caseComposite((Composite) widget, args);
    }

    public T caseComposite(Composite widget, Object... args) {
        return caseScrollable((Scrollable) widget, args);
    }

    public T caseScrollable(Scrollable widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseScale(Scale widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseSash(Sash widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseProgressBar(ProgressBar widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseLink(Link widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseLabel(Label widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseButton(Button widget, Object... args) {
        return caseControl((Control) widget, args);
    }

    public T caseControl(Control widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseCaret(Caret widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseDropTarget(DropTarget widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseDragSource(DragSource widget, Object... args) {
        return caseWidget((Widget) widget, args);
    }

    public T caseWidget(Widget widget, Object... args) {
        return null;
    }

    public T doSwitch(Widget widget, Object... args) {
        T result = null;
        if (widget instanceof Tray) {
            return caseTray((Tray) widget, args);
        } else if (widget instanceof Tracker) {
            return caseTracker((Tracker) widget, args);
        } else if (widget instanceof ToolTip) {
            return caseToolTip((ToolTip) widget, args);
        } else if (widget instanceof ScrollBar) {
            return caseScrollBar((ScrollBar) widget, args);
        } else if (widget instanceof Menu) {
            return caseMenu((Menu) widget, args);
        } else if (widget instanceof CTabItem) {
            return caseCTabItem((CTabItem) widget, args);
        } else if (widget instanceof TreeItem) {
            return caseTreeItem((TreeItem) widget, args);
        } else if (widget instanceof TreeColumn) {
            return caseTreeColumn((TreeColumn) widget, args);
        } else if (widget instanceof TrayItem) {
            return caseTrayItem((TrayItem) widget, args);
        } else if (widget instanceof ToolItem) {
            return caseToolItem((ToolItem) widget, args);
        } else if (widget instanceof TableItem) {
            return caseTableItem((TableItem) widget, args);
        } else if (widget instanceof TableColumn) {
            return caseTableColumn((TableColumn) widget, args);
        } else if (widget instanceof TabItem) {
            return caseTabItem((TabItem) widget, args);
        } else if (widget instanceof MenuItem) {
            return caseMenuItem((MenuItem) widget, args);
        } else if (widget instanceof ExpandItem) {
            return caseExpandItem((ExpandItem) widget, args);
        } else if (widget instanceof CoolItem) {
            return caseCoolItem((CoolItem) widget, args);
        } else if (widget instanceof Item) {
            return caseItem((Item) widget, args);
        } else if (widget instanceof IME) {
            return caseIME((IME) widget, args);
        } else if (widget instanceof Slider) {
            return caseSlider((Slider) widget, args);
        } else if (widget instanceof Text) {
            return caseText((Text) widget, args);
        } else if (widget instanceof List) {
            return caseList((List) widget, args);
        } else if (widget instanceof Form) {
            return caseForm((Form) widget, args);
        } else if (widget instanceof PageBook) {
            return casePageBook((PageBook) widget, args);
        } else if (widget instanceof DrillDownComposite) {
            return caseDrillDownComposite((DrillDownComposite) widget, args);
        } else if (widget instanceof FilteredTree) {
            return caseFilteredTree((FilteredTree) widget, args);
        } else if (widget instanceof FilteredList) {
            return caseFilteredList((FilteredList) widget, args);
        } else if (widget instanceof ProgressMonitorPart) {
            return caseProgressMonitorPart((ProgressMonitorPart) widget, args);
        } else if (widget instanceof ProgressIndicator) {
            return caseProgressIndicator((ProgressIndicator) widget, args);
        } else if (widget instanceof Browser) {
            return caseBrowser((Browser) widget, args);
        } else if (widget instanceof OleFrame) {
            return caseOleFrame((OleFrame) widget, args);
        } else if (widget instanceof OleControlSite) {
            return caseOleControlSite((OleControlSite) widget, args);
        } else if (widget instanceof OleClientSite) {
            return caseOleClientSite((OleClientSite) widget, args);
        } else if (widget instanceof ViewForm) {
            return caseViewForm((ViewForm) widget, args);
        } else if (widget instanceof ScrolledPageBook) {
            return caseScrolledPageBook((ScrolledPageBook) widget, args);
        } else if (widget instanceof ScrolledFormText) {
            return caseScrolledFormText((ScrolledFormText) widget, args);
        } else if (widget instanceof ScrolledForm) {
            return caseScrolledForm((ScrolledForm) widget, args);
        } else if (widget instanceof SharedScrolledComposite) {
            return caseSharedScrolledComposite((SharedScrolledComposite) widget, args);
        } else if (widget instanceof ScrolledComposite) {
            return caseScrolledComposite((ScrolledComposite) widget, args);
        } else if (widget instanceof SashForm) {
            return caseSashForm((SashForm) widget, args);
        } else if (widget instanceof CTabFolder) {
            return caseCTabFolder((CTabFolder) widget, args);
        } else if (widget instanceof CCombo) {
            return caseCCombo((CCombo) widget, args);
        } else if (widget instanceof CBanner) {
            return caseCBanner((CBanner) widget, args);
        } else if (widget instanceof Tree) {
            return caseTree((Tree) widget, args);
        } else if (widget instanceof ToolBar) {
            return caseToolBar((ToolBar) widget, args);
        } else if (widget instanceof Table) {
            return caseTable((Table) widget, args);
        } else if (widget instanceof TabFolder) {
            return caseTabFolder((TabFolder) widget, args);
        } else if (widget instanceof Spinner) {
            return caseSpinner((Spinner) widget, args);
        } else if (widget instanceof Group) {
            return caseGroup((Group) widget, args);
        } else if (widget instanceof ExpandBar) {
            return caseExpandBar((ExpandBar) widget, args);
        } else if (widget instanceof DateTime) {
            return caseDateTime((DateTime) widget, args);
        } else if (widget instanceof CoolBar) {
            return caseCoolBar((CoolBar) widget, args);
        } else if (widget instanceof Combo) {
            return caseCombo((Combo) widget, args);
        } else if (widget instanceof FormText) {
            return caseFormText((FormText) widget, args);
        } else if (widget instanceof Section) {
            return caseSection((Section) widget, args);
        } else if (widget instanceof ExpandableComposite) {
            return caseExpandableComposite((ExpandableComposite) widget, args);
        } else if (widget instanceof Twistie) {
            return caseTwistie((Twistie) widget, args);
        } else if (widget instanceof TreeNode) {
            return caseTreeNode((TreeNode) widget, args);
        } else if (widget instanceof ToggleHyperlink) {
            return caseToggleHyperlink((ToggleHyperlink) widget, args);
        } else if (widget instanceof ImageHyperlink) {
            return caseImageHyperlink((ImageHyperlink) widget, args);
        } else if (widget instanceof Hyperlink) {
            return caseHyperlink((Hyperlink) widget, args);
        } else if (widget instanceof AbstractHyperlink) {
            return caseAbstractHyperlink((AbstractHyperlink) widget, args);
        } else if (widget instanceof GLCanvas) {
            return caseGLCanvas((GLCanvas) widget, args);
        } else if (widget instanceof TableCursor) {
            return caseTableCursor((TableCursor) widget, args);
        } else if (widget instanceof StyledText) {
            return caseStyledText((StyledText) widget, args);
        } else if (widget instanceof CLabel) {
            return caseCLabel((CLabel) widget, args);
        } else if (widget instanceof Shell) {
            return caseShell((Shell) widget, args);
        } else if (widget instanceof Decorations) {
            return caseDecorations((Decorations) widget, args);
        } else if (widget instanceof Canvas) {
            return caseCanvas((Canvas) widget, args);
        } else if (widget instanceof Composite) {
            return caseComposite((Composite) widget, args);
        } else if (widget instanceof Scrollable) {
            return caseScrollable((Scrollable) widget, args);
        } else if (widget instanceof Scale) {
            return caseScale((Scale) widget, args);
        } else if (widget instanceof Sash) {
            return caseSash((Sash) widget, args);
        } else if (widget instanceof ProgressBar) {
            return caseProgressBar((ProgressBar) widget, args);
        } else if (widget instanceof Link) {
            return caseLink((Link) widget, args);
        } else if (widget instanceof Label) {
            return caseLabel((Label) widget, args);
        } else if (widget instanceof Button) {
            return caseButton((Button) widget, args);
        } else if (widget instanceof Control) {
            return caseControl((Control) widget, args);
        } else if (widget instanceof Caret) {
            return caseCaret((Caret) widget, args);
        } else if (widget instanceof DropTarget) {
            return caseDropTarget((DropTarget) widget, args);
        } else if (widget instanceof DragSource) {
            return caseDragSource((DragSource) widget, args);
        } else {
            result = caseWidget(widget, args);
        }
        return result;
    }
}
