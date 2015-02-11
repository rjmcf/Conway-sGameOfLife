package uk.ac.cam.rjm232.tick7;

//TODO:  specify the appropriate import statements
import uk.ac.cam.acr31.life.World;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class PatternPanel extends JPanel {
 	private JList guiList;
 	private Pattern currentPattern;
 	private List<Pattern> patternList;
 	
 	
 	public PatternPanel() {
  		super();
  		setLayout(new BorderLayout());
  		guiList = new JList();
  		guiList.addListSelectionListener(new ListSelectionListener() {
     		public void valueChanged(ListSelectionEvent e) {
      			if (!e.getValueIsAdjusting() && (patternList != null)) {
       				int sel = guiList.getSelectedIndex();
       				if (sel != -1) {
        				currentPattern = patternList.get(sel);
        				onPatternChange();
       				}
				} 
			}
		});
  		add(new JScrollPane(guiList));
  		currentPattern = null;
	}
	
 	public void setPatterns(List<Pattern> list) {
 	 	if (list == null) {
       		currentPattern = null; //if list is null, then no valid pattern
       		guiList.setListData(new String[]{}); //no list item to select
       		return;
		}
  		ArrayList<String> names = new ArrayList<String>();
  		for (Pattern pat : list) {
  			names.add(pat.getName() + " (" + pat.getAuthor() +")");
  		}
  		patternList = list;
  		guiList.setListData(names.toArray());
  		currentPattern = list.get(0); //select first element in list
      	guiList.setSelectedIndex(0);  //select first element in guiList
 	}
 	
 	public Pattern getCurrentPattern() {
 		return currentPattern;
 	}
 	
 	protected abstract void onPatternChange();
}