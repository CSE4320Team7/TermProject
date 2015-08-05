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

	public JPanel contentPane;
	
	/**
	 * Alert Panel Components
	 */
	public JPanel panelAlertPanel;
	public JLabel lblAlertPanel;
	public JTextField textFieldA1;
	public JTextField textFieldB1;
	public JTextField textFieldC1;
	public JTextField textFieldD1;
	public JTextField textFieldA2;
	public JTextField textFieldB2;
	public JTextField textFieldC2;
	public JTextField textFieldD2;
	public JTextField textFieldA3;
	public JTextField textFieldB3;
	public JTextField textFieldC3;
	public JTextField textFieldD3;
	
	/**
	 * Display Panel Components
	 */
	public JPanel panelDisplayPanel;
	public JLabel lblAlt;
	public JTextField textFieldAltitude;
	public JLabel lblFt;
	public JLabel lblVel;
	public JTextField textFieldVelocity;
	public JLabel lblFtPerSec;
	public JLabel lblMotorProgram;
	public JTextField textFieldMotorProgram;
	public JLabel lblPodPosition;
	public JTextField textFieldPodPosition;
	public JLabel lblFuelRemaining;
	public JLabel lblGallons;
	public JTextField textFieldFuelRemaining;
	public JLabel lblAttitude;
	public JLabel lblAttDegrees;
	public JTextField textFieldAttitude;
	public JLabel lblGroundAttitude;
	public JTextField textFieldGroundAttitude;
	public JLabel lblGroundAttDegrees;
	
	/**
	 * Pod Control Panel Components
	 */
	public JPanel panelPodPosPanel;
	public JLabel lblPodBtn;
	public JToggleButton tglbtnPodUpToggleButton;
	public JToggleButton tglbtnPodDownToggleButton;
	
	
	private int landerAttitude = 0;


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
		
		JPanel panelAlertPanel = new JPanel();
		panelAlertPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAlertPanel.setBounds(489, 67, 733, 114);
		contentPane.add(panelAlertPanel);
		
		textFieldA1 = new JTextField();
		textFieldA1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA1.setText("FLT20");
		panelAlertPanel.add(textFieldA1);
		textFieldA1.setColumns(10);
		
		textFieldB1 = new JTextField();
		textFieldB1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldB1.setText("POS");
		panelAlertPanel.add(textFieldB1);
		textFieldB1.setColumns(10);
		
		textFieldC1 = new JTextField();
		textFieldC1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldC1.setText("PDMG");
		panelAlertPanel.add(textFieldC1);
		textFieldC1.setColumns(10);
		
		textFieldD1 = new JTextField();
		textFieldD1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldD1.setText("LC");
		panelAlertPanel.add(textFieldD1);
		textFieldD1.setColumns(10);
		
		textFieldA2 = new JTextField();
		textFieldA2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA2.setText("EPD");
		panelAlertPanel.add(textFieldA2);
		textFieldA2.setColumns(10);
		
		textFieldB2 = new JTextField();
		textFieldB2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB2.setHorizontalAlignment(SwingConstants.CENTER);
		panelAlertPanel.add(textFieldB2);
		textFieldB2.setColumns(10);
		
		textFieldC2 = new JTextField();
		textFieldC2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC2.setHorizontalAlignment(SwingConstants.CENTER);
		panelAlertPanel.add(textFieldC2);
		textFieldC2.setColumns(10);
		
		textFieldD2 = new JTextField();
		textFieldD2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD2.setHorizontalAlignment(SwingConstants.CENTER);
		panelAlertPanel.add(textFieldD2);
		textFieldD2.setColumns(10);
		
		textFieldA3 = new JTextField();
		textFieldA3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldA3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldA3.setText("IDPZ");
		panelAlertPanel.add(textFieldA3);
		textFieldA3.setColumns(10);
		
		textFieldB3 = new JTextField();
		textFieldB3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldB3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldB3.setText("LOS");
		panelAlertPanel.add(textFieldB3);
		textFieldB3.setColumns(10);
		
		textFieldC3 = new JTextField();
		textFieldC3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldC3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldC3.setText("PODPOS: DOWN");
		panelAlertPanel.add(textFieldC3);
		textFieldC3.setColumns(10);
		
		textFieldD3 = new JTextField();
		textFieldD3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldD3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldD3.setText("PODCMD: DOWN");
		panelAlertPanel.add(textFieldD3);
		textFieldD3.setColumns(10);
		
		panelDisplayPanel = new JPanel();
		panelDisplayPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelDisplayPanel.setBounds(92, 124, 351, 300);
		contentPane.add(panelDisplayPanel);
		panelDisplayPanel.setLayout(null);
		
		lblAlt = new JLabel("Alt");
		lblAlt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlt.setBounds(12, 8, 61, 16);
		panelDisplayPanel.add(lblAlt);
		
		textFieldAltitude = new JTextField();
		textFieldAltitude.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldAltitude.setBounds(65, 5, 101, 22);
		panelDisplayPanel.add(textFieldAltitude);
		textFieldAltitude.setColumns(5);
		
		lblFt = new JLabel("ft");
		lblFt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFt.setBounds(188, 8, 20, 16);
		panelDisplayPanel.add(lblFt);
		
		lblVel = new JLabel("Vel");
		lblVel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVel.setBounds(12, 37, 39, 16);
		panelDisplayPanel.add(lblVel);
		
		textFieldVelocity = new JTextField();
		textFieldVelocity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldVelocity.setBounds(65, 34, 101, 22);
		panelDisplayPanel.add(textFieldVelocity);
		textFieldVelocity.setColumns(5);
		
		lblFtPerSec = new JLabel("ft/s");
		lblFtPerSec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFtPerSec.setBounds(188, 37, 45, 16);
		panelDisplayPanel.add(lblFtPerSec);
		
		lblMotorProgram = new JLabel("Motor Program");
		lblMotorProgram.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMotorProgram.setBounds(12, 65, 137, 22);
		panelDisplayPanel.add(lblMotorProgram);
		
		textFieldMotorProgram = new JTextField();
		textFieldMotorProgram.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMotorProgram.setBounds(161, 64, 61, 22);
		panelDisplayPanel.add(textFieldMotorProgram);
		textFieldMotorProgram.setColumns(5);
		
		lblPodPosition = new JLabel("Pod Position");
		lblPodPosition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPodPosition.setBounds(12, 96, 115, 16);
		panelDisplayPanel.add(lblPodPosition);
		
		textFieldPodPosition = new JTextField();
		textFieldPodPosition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldPodPosition.setBounds(161, 93, 61, 22);
		panelDisplayPanel.add(textFieldPodPosition);
		textFieldPodPosition.setColumns(5);
		
		lblFuelRemaining = new JLabel("Fuel Remaining");
		lblFuelRemaining.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFuelRemaining.setBounds(12, 125, 154, 19);
		panelDisplayPanel.add(lblFuelRemaining);
		
		textFieldFuelRemaining = new JTextField();
		textFieldFuelRemaining.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFuelRemaining.setBounds(161, 122, 61, 22);
		panelDisplayPanel.add(textFieldFuelRemaining);
		textFieldFuelRemaining.setColumns(5);
		
		lblAttitude = new JLabel("Attitude");
		lblAttitude.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAttitude.setBounds(12, 153, 115, 16);
		panelDisplayPanel.add(lblAttitude);
		
		textFieldAttitude = new JTextField();
		textFieldAttitude.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldAttitude.setBounds(161, 149, 61, 22);
		panelDisplayPanel.add(textFieldAttitude);
		textFieldAttitude.setColumns(5);
		
		lblGroundAttitude = new JLabel("Ground Attitude");
		lblGroundAttitude.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroundAttitude.setBounds(12, 182, 137, 16);
		panelDisplayPanel.add(lblGroundAttitude);
		
		textFieldGroundAttitude = new JTextField();
		textFieldGroundAttitude.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldGroundAttitude.setBounds(161, 178, 61, 22);
		panelDisplayPanel.add(textFieldGroundAttitude);
		textFieldGroundAttitude.setColumns(5);
		
		lblGallons = new JLabel("Gallons");
		lblGallons.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGallons.setBounds(234, 126, 74, 16);
		panelDisplayPanel.add(lblGallons);
		
		lblAttDegrees = new JLabel("Degrees");
		lblAttDegrees.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAttDegrees.setBounds(234, 150, 77, 22);
		panelDisplayPanel.add(lblAttDegrees);
		
		lblGroundAttDegrees = new JLabel("Degrees");
		lblGroundAttDegrees.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroundAttDegrees.setBounds(234, 179, 74, 22);
		panelDisplayPanel.add(lblGroundAttDegrees);
		
		JPanel panelAttitudePanel = new JPanel();
		panelAttitudePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAttitudePanel.setBounds(496, 258, 439, 232);
		contentPane.add(panelAttitudePanel);
		panelAttitudePanel.setLayout(null);
		
		JLabel lblMinus = new JLabel("-");
		lblMinus.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinus.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMinus.setBounds(12, 104, 56, 29);
		panelAttitudePanel.add(lblMinus);
		
		JButton tglbtnDecreaseAttitudeToggleButton = new JButton("<");
		tglbtnDecreaseAttitudeToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnDecreaseAttitudeToggleButton.setBounds(80, 38, 137, 149);
		tglbtnDecreaseAttitudeToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adjustLanderAttitude(-1);
			}
		});
		panelAttitudePanel.add(tglbtnDecreaseAttitudeToggleButton);
		
		JButton tglbtnIncreaseAttitudeToggleButton_1 = new JButton(">");
		tglbtnIncreaseAttitudeToggleButton_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnIncreaseAttitudeToggleButton_1.setBounds(222, 38, 137, 149);
		tglbtnIncreaseAttitudeToggleButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adjustLanderAttitude(1);
			}
		});
		panelAttitudePanel.add(tglbtnIncreaseAttitudeToggleButton_1);
		
		JLabel lblPlus = new JLabel("+");
		lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlus.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPlus.setBounds(371, 104, 56, 29);
		panelAttitudePanel.add(lblPlus);
		
		JLabel lblAttitudeControl = new JLabel("ATTITUDE CONTROL");
		lblAttitudeControl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAttitudeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttitudeControl.setBounds(68, 195, 307, 24);
		panelAttitudePanel.add(lblAttitudeControl);
		
		JPanel panelPodPosPanel = new JPanel();
		panelPodPosPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelPodPosPanel.setBounds(1055, 220, 237, 277);
		contentPane.add(panelPodPosPanel);
		panelPodPosPanel.setLayout(null);
		
		lblPodBtn = new JLabel("PODBTN");
		lblPodBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPodBtn.setBounds(89, 13, 83, 16);
		panelPodPosPanel.add(lblPodBtn);
		
		tglbtnPodUpToggleButton = new JToggleButton("U");
		tglbtnPodUpToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnPodUpToggleButton.setBounds(42, 42, 160, 107);
		tglbtnPodUpToggleButton.setSelected(true);
		tglbtnPodUpToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnPodDownToggleButton.setSelected(false);
			}
		});
		panelPodPosPanel.add(tglbtnPodUpToggleButton);
		
		tglbtnPodDownToggleButton = new JToggleButton("D");
		tglbtnPodDownToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tglbtnPodDownToggleButton.setBounds(42, 157, 160, 107);
		tglbtnPodDownToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnPodUpToggleButton.setSelected(false);
			}
		});
		panelPodPosPanel.add(tglbtnPodDownToggleButton);
		
		lblAlertPanel = new JLabel("ALERT PANEL");
		lblAlertPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAlertPanel.setBounds(766, 30, 188, 37);
		contentPane.add(lblAlertPanel);
		
		JLabel lblDisplayPanel = new JLabel("DISPLAY PANEL");
		lblDisplayPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDisplayPanel.setBounds(157, 86, 228, 37);
		contentPane.add(lblDisplayPanel);
	}
	
	/**
	 * set the Up / Down pod button through code
	 * @param test true if pod is activated.
	 */
	public void setButton(boolean test) {
		if(test) {
			tglbtnPodUpToggleButton.setSelected(false);
			tglbtnPodDownToggleButton.setSelected(true);
		} else {
			tglbtnPodUpToggleButton.setSelected(true);
			tglbtnPodDownToggleButton.setSelected(false);
		}
	}
	
	public int getLanderAttitude() {
		return this.landerAttitude;
	}
	public void adjustLanderAttitude(int tilt) {
		this.landerAttitude += tilt;
	}
	public boolean getButtonStatus() {
		return tglbtnPodDownToggleButton.isSelected();
	}
}
