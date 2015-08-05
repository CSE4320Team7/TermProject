package TermProject;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class LanderDisplay extends JFrame {

	/**private JPanel contentPane;
	private JTextField textFieldC1;
	private JTextField textField_1;
	private JTextField textFieldB1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField placeholderTextField_2;
	private JTextField textFieldD1;
	private JTextField textFieldD3;
	private JTextField placeholderTextField;
	private JTextField textField_10;
	private JTextField textFieldA1;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textFieldD2;
	private JTextField textFieldC2;
	private JTextField textFieldB2;
	private JTextField txtEpd;
	private JTextField textFieldA2;
	private JTextField textFieldC3;
	private JTextField textFieldB3;
	private JTextField textFieldA3;
	private JTextField textField_22;
	private JTextField textField_23;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField textField_24;
	private JLabel lblNewLabel_1;
	private JTextField textField_25;
	private JLabel lblNewLabel_2;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JLabel lblNewLabel_5;
	private JTextField textField_29;
	private JTextField textField_30;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_14;
	private JToggleButton tglbtnNewToggleButton_2;
	private JToggleButton tglbtnD;
	private JLabel lblAlertPanel;*/
	public JPanel contentPane;
	public JTextField textFieldC1;
	public JTextField textFieldB1;
	public JTextField textFieldD1;
	public JTextField textFieldD3;
	public JTextField textFieldA1;
	public JTextField textFieldD2;
	public JTextField textFieldC2;
	public JTextField textFieldB2;
	public JTextField textFieldA2;
	public JTextField textFieldC3;
	public JTextField textFieldB3;
	public JTextField textFieldA3;
	public JPanel panel_1;
	public JLabel lblNewLabel;
	public JTextField textField_24;
	public JLabel lblNewLabel_1;
	public JTextField textField_25;
	public JLabel lblNewLabel_2;
	public JTextField textField_26;
	public JTextField textField_27;
	public JTextField textField_28;
	public JLabel lblNewLabel_5;
	public JTextField textField_29;
	public JTextField textField_30;
	public JLabel lblNewLabel_7;
	public JLabel lblNewLabel_8;
	public JLabel lblNewLabel_9;
	public JLabel lblNewLabel_10;
	public JLabel lblNewLabel_12;
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_6;
	public JLabel lblNewLabel_14;
	public JToggleButton tglbtnNewToggleButton_2;
	public JToggleButton tglbtnD;
	public JLabel lblAlertPanel;
	
	private int landerAttitude = 0;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LanderDisplay frame = new LanderDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public LanderDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1342, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel alertPanel = new JPanel();
		alertPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		alertPanel.setBounds(489, 67, 733, 114);
		contentPane.add(alertPanel);
		
		textFieldA1 = new JTextField();
		textFieldA1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA1.setText("FLT20");
		alertPanel.add(textFieldA1);
		textFieldA1.setColumns(10);
		
		textFieldB1 = new JTextField();
		textFieldB1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldB1.setText("POS");
		alertPanel.add(textFieldB1);
		textFieldB1.setColumns(10);
		
		textFieldC1 = new JTextField();
		textFieldC1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldC1.setText("PDMG");
		alertPanel.add(textFieldC1);
		textFieldC1.setColumns(10);
		
		textFieldD1 = new JTextField();
		textFieldD1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldD1.setText("LC");
		alertPanel.add(textFieldD1);
		textFieldD1.setColumns(10);
		
		textFieldA2 = new JTextField();
		textFieldA2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA2.setText("EPD");
		alertPanel.add(textFieldA2);
		textFieldA2.setColumns(10);
		
		textFieldB2 = new JTextField();
		textFieldB2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB2.setHorizontalAlignment(SwingConstants.CENTER);
		alertPanel.add(textFieldB2);
		textFieldB2.setColumns(10);
		
		textFieldC2 = new JTextField();
		textFieldC2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC2.setHorizontalAlignment(SwingConstants.CENTER);
		alertPanel.add(textFieldC2);
		textFieldC2.setColumns(10);
		
		textFieldD2 = new JTextField();
		textFieldD2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD2.setHorizontalAlignment(SwingConstants.CENTER);
		alertPanel.add(textFieldD2);
		textFieldD2.setColumns(10);
		
		textFieldA3 = new JTextField();
		textFieldA3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA3.setText("IDPZ");
		alertPanel.add(textFieldA3);
		textFieldA3.setColumns(10);
		
		textFieldB3 = new JTextField();
		textFieldB3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldB3.setText("LOS");
		alertPanel.add(textFieldB3);
		textFieldB3.setColumns(10);
		
		textFieldC3 = new JTextField();
		textFieldC3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldC3.setText("PODPOS: DOWN");
		alertPanel.add(textFieldC3);
		textFieldC3.setColumns(10);
		
		textFieldD3 = new JTextField();
		textFieldD3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldD3.setText("PODCMD: DOWN");
		alertPanel.add(textFieldD3);
		textFieldD3.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(135, 124, 279, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("Alt");
		lblNewLabel.setBounds(12, 8, 15, 16);
		panel_1.add(lblNewLabel);
		
		textField_24 = new JTextField();
		textField_24.setBounds(39, 5, 61, 22);
		panel_1.add(textField_24);
		textField_24.setColumns(5);
		
		lblNewLabel_7 = new JLabel("ft");
		lblNewLabel_7.setBounds(112, 8, 8, 16);
		panel_1.add(lblNewLabel_7);
		
		lblNewLabel_1 = new JLabel("Vel");
		lblNewLabel_1.setBounds(12, 37, 18, 16);
		panel_1.add(lblNewLabel_1);
		
		textField_25 = new JTextField();
		textField_25.setBounds(39, 32, 61, 22);
		panel_1.add(textField_25);
		textField_25.setColumns(5);
		
		lblNewLabel_2 = new JLabel("ft/s");
		lblNewLabel_2.setBounds(113, 37, 19, 16);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_8 = new JLabel("Motor Program");
		lblNewLabel_8.setBounds(12, 67, 86, 16);
		panel_1.add(lblNewLabel_8);
		
		textField_26 = new JTextField();
		textField_26.setBounds(139, 64, 61, 22);
		panel_1.add(textField_26);
		textField_26.setColumns(5);
		
		lblNewLabel_9 = new JLabel("Pod Position");
		lblNewLabel_9.setBounds(12, 96, 88, 16);
		panel_1.add(lblNewLabel_9);
		
		textField_27 = new JTextField();
		textField_27.setBounds(139, 93, 61, 22);
		panel_1.add(textField_27);
		textField_27.setColumns(5);
		
		lblNewLabel_10 = new JLabel("Fuel Remaining");
		lblNewLabel_10.setBounds(12, 125, 103, 16);
		panel_1.add(lblNewLabel_10);
		
		textField_28 = new JTextField();
		textField_28.setBounds(139, 122, 61, 22);
		panel_1.add(textField_28);
		textField_28.setColumns(5);
		
		lblNewLabel_5 = new JLabel("Attitude");
		lblNewLabel_5.setBounds(12, 153, 56, 16);
		panel_1.add(lblNewLabel_5);
		
		textField_29 = new JTextField();
		textField_29.setBounds(139, 150, 61, 22);
		panel_1.add(textField_29);
		textField_29.setColumns(5);
		
		lblNewLabel_12 = new JLabel("Ground Attitude");
		lblNewLabel_12.setBounds(12, 182, 101, 16);
		panel_1.add(lblNewLabel_12);
		
		textField_30 = new JTextField();
		textField_30.setBounds(139, 179, 61, 22);
		panel_1.add(textField_30);
		textField_30.setColumns(5);
		
		lblNewLabel_3 = new JLabel("Gallons");
		lblNewLabel_3.setBounds(211, 125, 56, 16);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Degrees");
		lblNewLabel_4.setBounds(212, 153, 56, 16);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_6 = new JLabel("Degrees");
		lblNewLabel_6.setBounds(211, 182, 56, 16);
		panel_1.add(lblNewLabel_6);
		
		JPanel attitudePanel = new JPanel();
		attitudePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		attitudePanel.setBounds(496, 258, 439, 232);
		contentPane.add(attitudePanel);
		attitudePanel.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("-");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_11.setBounds(12, 104, 56, 29);
		attitudePanel.add(lblNewLabel_11);
		
		JButton tglbtnNewToggleButton = new JButton("<");
		tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnNewToggleButton.setBounds(80, 38, 137, 149);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adjustLanderAttitude(-1);
			}
		});
		attitudePanel.add(tglbtnNewToggleButton);
		
		JButton tglbtnNewToggleButton_1 = new JButton(">");
		tglbtnNewToggleButton_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnNewToggleButton_1.setBounds(222, 38, 137, 149);
		tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adjustLanderAttitude(1);
			}
		});
		attitudePanel.add(tglbtnNewToggleButton_1);
		
		JLabel lblNewLabel_13 = new JLabel("+");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_13.setBounds(371, 104, 56, 29);
		attitudePanel.add(lblNewLabel_13);
		
		JLabel lblAttitudeControl = new JLabel("ATTITUDE CONTROL");
		lblAttitudeControl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAttitudeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttitudeControl.setBounds(68, 195, 307, 24);
		attitudePanel.add(lblAttitudeControl);
		
		JPanel podPosPanel = new JPanel();
		podPosPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		podPosPanel.setBounds(1055, 220, 237, 277);
		contentPane.add(podPosPanel);
		podPosPanel.setLayout(null);
		
		lblNewLabel_14 = new JLabel("PODBTN");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setBounds(89, 13, 83, 16);
		podPosPanel.add(lblNewLabel_14);
		
		tglbtnNewToggleButton_2 = new JToggleButton("U");
		tglbtnNewToggleButton_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnNewToggleButton_2.setBounds(42, 42, 160, 107);
		tglbtnNewToggleButton_2.setSelected(true);
		tglbtnNewToggleButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnD.setSelected(false);
			}
		});
		podPosPanel.add(tglbtnNewToggleButton_2);
		
		tglbtnD = new JToggleButton("D");
		tglbtnD.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnD.setBounds(42, 157, 160, 107);
		tglbtnD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnNewToggleButton_2.setSelected(false);
			}
		});
		podPosPanel.add(tglbtnD);
		
		lblAlertPanel = new JLabel("ALERT PANEL");
		lblAlertPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAlertPanel.setBounds(766, 30, 188, 37);
		contentPane.add(lblAlertPanel);
		
		JLabel lblDisplayPanel = new JLabel("DISPLAY PANEL");
		lblDisplayPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDisplayPanel.setBounds(170, 84, 228, 37);
		contentPane.add(lblDisplayPanel);
	}
	
	/**
	 * set the Up / Down pod button through code
	 * @param test true if pod is activated.
	 */
	public void setButton(boolean test) {
		if(test) {
			tglbtnNewToggleButton_2.setSelected(false);
			tglbtnD.setSelected(true);
		} else {
			tglbtnNewToggleButton_2.setSelected(true);
			tglbtnD.setSelected(false);
		}
	}
	
	public int getLanderAttitude() {
		return this.landerAttitude;
	}
	public void adjustLanderAttitude(int tilt) {
		this.landerAttitude += tilt;
	}
	public boolean getButtonStatus() {
		return tglbtnD.isSelected();
	}
}
