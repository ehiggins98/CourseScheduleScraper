package com.github.beijingstrongbow.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.github.beijingstrongbow.Section;
import com.github.beijingstrongbow.userinterface.managers.ScheduleViewerManager;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Toolkit;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ScheduleViewerWindow {

	private JFrame frame;
	private JTextField uxCourseNumberField;
	private JTextField uxSectionTypeField;
	private JTextField uxStartTimeField;
	private JTextField uxSectionNumberField;
	private JTextField uxInstructorField;
	
	private JList<String> uxSchedulesList;
	
	private ScheduleViewerManager manager;
	
	private DefaultListModel<String> schedules;
	
	private DefaultListModel<Section> details;
	
	private JScrollPane detailsScrollPane;
	
	private JList<Section> uxDetailsList;
	
	private JLabel uxAppointmentWarning;
	
	private final String appointmentWarning = "*There is an appointment section not shown";
	
	private final String appointmentsWarning = "*There are appointment sections not shown";

	/**
	 * Create the application.
	 */
	public ScheduleViewerWindow(ScheduleViewerManager manager) {
		schedules = new DefaultListModel<String>();
		details = new DefaultListModel<Section>();
		
		this.manager = manager;
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				initialize();
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.PREF_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("120dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("15dlu"),
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("120dlu"),
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("12dlu"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("105dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("105dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,}));
		
		JLabel uxSchedulesLabel = new JLabel("Schedules");
		uxSchedulesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxSchedulesLabel, "4, 2");
		
		JLabel uxDetailsLabel = new JLabel("Details");
		uxDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxDetailsLabel, "8, 2");
		
		uxSchedulesList = new JList<String>();
		uxSchedulesList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uxSchedulesList.setModel(schedules);
		uxSchedulesList.addMouseListener(manager.new ShowCalendarListener());
		uxSchedulesList.addKeyListener(manager.new ShowCalendarListener());
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setViewportView(uxSchedulesList);
		frame.getContentPane().add(scrollPane1, "4, 4, 1, 4");
		
		uxDetailsList = new JList<Section>();
		uxDetailsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uxDetailsList.setModel(details);
		
		detailsScrollPane = new JScrollPane();
		detailsScrollPane.setViewportView(uxDetailsList);
		frame.getContentPane().add(detailsScrollPane, "8, 4, 7, 4, fill, fill");
		
		JButton uxViewDetailsButton = new JButton("View Details"); 
		uxViewDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxViewDetailsButton, "6, 6");
		uxViewDetailsButton.addActionListener(manager.new ShowDetailsListener());
		
		JButton uxViewCalendarButton = new JButton("View Calendar");
		uxViewCalendarButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxViewCalendarButton, "6, 5");
		uxViewCalendarButton.addActionListener(manager.new ShowCalendarListener());
		
		JLabel uxNarrowResultsLabel = new JLabel("Narrow Results");
		uxNarrowResultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxNarrowResultsLabel, "4, 9, 3, 1");
		
		uxAppointmentWarning = new JLabel();
		uxAppointmentWarning.setFont(new Font("Tahoma", Font.BOLD, 14));
		uxAppointmentWarning.setForeground(Color.RED);
		frame.getContentPane().add(uxAppointmentWarning, "12, 9, 3, 1");
		
		JLabel uxCourseNumberLabel = new JLabel("Course Number");
		uxCourseNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxCourseNumberLabel, "4, 11, 3, 1");
		
		JLabel uxStartTimeLabel = new JLabel("Start Time");
		uxStartTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxStartTimeLabel, "8, 11");
		
		JLabel uxInstructorLabel = new JLabel("Instructor");
		uxInstructorLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxInstructorLabel, "10, 11");
		
		JLabel uxInstructorSubtitle = new JLabel("Exactly as in course catalog");
		frame.getContentPane().add(uxInstructorSubtitle, "11, 11");
		
		uxCourseNumberField = new JTextField();
		uxCourseNumberField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxCourseNumberField, "4, 12, 3, 1, fill, default");
		uxCourseNumberField.setColumns(10);
		
		uxStartTimeField = new JTextField();
		uxStartTimeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uxStartTimeField.setText("(e.g. 3:40 p.m.)");
		frame.getContentPane().add(uxStartTimeField, "8, 12, fill, default");
		uxStartTimeField.setColumns(10);
		
		uxInstructorField = new JTextField();
		uxInstructorField.setText("(e.g. Higgins, Daniel A)");
		uxInstructorField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxInstructorField, "10, 12, 2, 1, fill, default");
		uxInstructorField.setColumns(10);
		
		JButton uxUseThisButton = new JButton("Use This");
		uxUseThisButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxUseThisButton, "13, 12");
		uxUseThisButton.addActionListener(manager.new NarrowResultsListener());
		
		JLabel uxSectionTypeLabel = new JLabel("Section Type");
		uxSectionTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxSectionTypeLabel, "4, 14");
		
		JLabel uxSectionTypeSubtitle = new JLabel("Only if Lab, Rec, Qz");
		frame.getContentPane().add(uxSectionTypeSubtitle, "6, 14");
		
		JLabel uxSectionNumberLabel = new JLabel("Section Number");
		uxSectionNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxSectionNumberLabel, "8, 14");
		
		uxSectionTypeField = new JTextField();
		uxSectionTypeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(uxSectionTypeField, "4, 15, 3, 1, fill, default");
		uxSectionTypeField.setColumns(10);
		
		uxSectionNumberField = new JTextField();
		uxSectionNumberField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uxSectionNumberField.setText("(e.g. 11451)");
		frame.getContentPane().add(uxSectionNumberField, "8, 15, fill, default");
		uxSectionNumberField.setColumns(10);
		
		frame.getRootPane().setDefaultButton(uxUseThisButton);
		frame.pack();
		
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setLocation((int) (0.5 * (screenSize.getWidth() - frameSize.getWidth())), (int) (0.5 * (screenSize.getHeight() - frameSize.getHeight())));
	}
	
	public void setVisible(boolean visible){
		frame.setVisible(visible);
	}
	
	public void setDefaultText(){
		uxInstructorField.setText("(e.g. Higgins, Daniel A)");
		uxSectionNumberField.setText("(e.g. 11451)");
		uxStartTimeField.setText("(e.g. 3:40 p.m.)");
		uxSectionTypeField.setText("");
	}
	
	public void dispose(){
		frame.dispose();
	}
	
	public DefaultListModel<String> getSchedulesList(){
		return schedules;
	}
	
	public DefaultListModel<Section> getDetailsList(){
		return details;
	}
	
	public String getCourseNumberText(){
		return uxCourseNumberField.getText();
	}
	
	public String getSectionTypeText(){
		return uxSectionTypeField.getText();
	}
	
	public String getStartTimeText(){
		return uxStartTimeField.getText();
	}
	
	public String getSectionNumberText(){
		return uxSectionNumberField.getText();
	}
	
	public String getInstructorText(){
		return uxInstructorField.getText();
	}
	
	public int getSelectedSchedule(){
		return uxSchedulesList.getSelectedIndex();
	}
	
	public void clearSearchFields(){
		uxInstructorField.setText("");
		uxSectionNumberField.setText("");
		uxSectionTypeField.setText("");
		uxStartTimeField.setText("");
	}
	
	public void viewCalendar(CalendarPanel calendar, int numAppointments){
		calendar.setSize(detailsScrollPane.getSize());
		detailsScrollPane.setViewportView(calendar);
		
		if(numAppointments <= 0) uxAppointmentWarning.setText("");

		else if(numAppointments == 1) uxAppointmentWarning.setText(appointmentWarning);
		
		else uxAppointmentWarning.setText(appointmentsWarning);
	}
	
	public void viewDetails(){
		detailsScrollPane.setViewportView(uxDetailsList);
		uxAppointmentWarning.setText("");
	}
}
