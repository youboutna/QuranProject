package info.usmans.QuranProject.application;

import info.usmans.QuranProject.controller.Loader;
import info.usmans.QuranProject.controller.QuranTextType;
import info.usmans.QuranProject.controller.QuranTranslationID;
import info.usmans.QuranProject.controller.QuranicFonts;
import info.usmans.QuranProject.model.Aya;
import info.usmans.QuranProject.model.JuzData;
import info.usmans.QuranProject.model.PageData;
import info.usmans.QuranProject.model.Quran;
import info.usmans.QuranProject.model.QuranData;
import info.usmans.QuranProject.model.Sura;
import info.usmans.QuranProject.model.SuraData;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -735356187246028596L;
	/**
	 * quranText[0] - Arabic Quran text quranText[1] - Translation for first
	 * column quranText[2] - Translation for second column
	 */
	private Quran[] quranText;
	/**
	 * Load Quran Meta Data
	 */
	private QuranData quranData;
	/**
	 * Number of total pages
	 */
	private int totalPages;

	// fonts and their sizes used by each panel.
	private final String col0FontFamily = Constants.me_quran_FontFamily;
	private int col0FontSize = 24;
	private final String col1FontFamily = Constants.KFGQPC_fontFamily;
	private int col1FontSize = 20;
	private final String col2FontFamily = Constants.KFGQPC_fontFamily;
	private int col2FontSize = 20;

	/**
	 * Map to use highligting ayas on each column. The key point contains surah,
	 * aya. The value point contains start position and end position
	 */
	@SuppressWarnings("unchecked")
	private Map<Point, Point> ayaOffsets[] = new Map[3];
	/**
	 * The highlighter color. We are using a custom extended class for easier
	 * cleanup.
	 */
	private Highlighter.HighlightPainter myHighlightPainter;
	/**
	 * The current location to highlight in main panel.
	 */
	private int highlightPos;

	// GUI components
	private JPanel topPanel;
	private JLabel lblSuraName;
	private JPanel bottomPanel;
	private JSpinner spinnerPage;
	private JLabel lblPage;
	private JLabel lblOf;
	private JTextPane txtpnData;
	private JLabel lblChapterNumber;
	private int[] startPagesofChapters;
	private JMenuBar menuBar;
	private JRadioButtonMenuItem rdbtnmntmUsmaniText;
	private JRadioButtonMenuItem rdbtnmntmSimpleText;
	private ButtonGroup btngrpMnQuranText;
	private JMenu mnIndex;
	private JMenu mnText;
	private JMenu mnSura;
	private JMenu mnPara;
	private JMenu mnInformation;
	private JSplitPane splitPane;
	private JSplitPane leftSplitPane;
	private JScrollPane scrollPane_Trans1;
	private JScrollPane scrollPane_Trans2;
	private JTextPane textPaneTrans1;
	private JTextPane textPaneTrans2;
	private JMenu mnTranslation;
	private JMenu menuTranslationCol1;
	private JMenu menuTranslationCol2;
	private JPanel pageSpinnerPanel;
	// private JPanel sorrahSpinnerPanel;
	// private JLabel lblBottomSoorah;
	// private JSpinner spinnerSoorah;
	private JMenuItem mntmQuranProject;
	private JPanel transLabelpanel2;
	private JComboBox<QuranTranslationID> comboBoxTranslation2;
	private JPanel transLabelpanel1;
	private JComboBox<QuranTranslationID> comboBoxTranslation1;
	private TranslationCol1 col1AL = new TranslationCol1();
	private TranslationCol2 col2AL = new TranslationCol2();
	private JSlider slider;
	private JSlider slider_1;
	private JSlider slider_2;
	private JLabel lblAyahSurah;

	private void preInit() {
		this.quranText = new Quran[3];
		this.quranData = Loader.getInstance().getQuranData();
		this.totalPages = quranData.getPages().getPageList().size();
		this.myHighlightPainter = new MyHighlightPainter(Color.LIGHT_GRAY);

	}

	public Main() {
		preInit();

		getContentPane().setComponentOrientation(
				ComponentOrientation.RIGHT_TO_LEFT);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBackground(Color.WHITE);
		setTitle("Quran Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topPanel = new JPanel();
		topPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		topPanel.setLayout(new BorderLayout(0, 0));

		bottomPanel = new JPanel();
		bottomPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.6);

		JPanel quranPanel = new JPanel();
		splitPane.setRightComponent(quranPanel);
		quranPanel.setLayout(new BorderLayout(0, 0));

		slider = new JSlider();
		quranPanel.add(slider, BorderLayout.SOUTH);
		slider.setFont(new Font("Dialog", Font.BOLD, 10));
		slider.setAlignmentX(Component.LEFT_ALIGNMENT);
		slider.setToolTipText("Font Size");
		slider.setSnapToTicks(true);
		slider.setValue(this.col0FontSize);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(4);
		slider.setMaximum(32);
		slider.setMinimum(8);

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// update the font size and reload the page
				if (!slider.getValueIsAdjusting()) {
					int size = slider.getValue();
					Main.this.col0FontSize = size;
					int _pagenum = ((Integer) Main.this.spinnerPage.getValue())
							.intValue();
					// TODO: Use SwingWorker thread
					Main.this.loadPage(0, _pagenum);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		quranPanel.add(scrollPane);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		txtpnData = new JTextPane();
		txtpnData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point pt = new Point(e.getX(), e.getY());
				highlightPos = txtpnData.viewToModel(pt);
				// TODO: Consider SwingWorker thread?
				highlightAya(highlightPos);
			}
		});
		txtpnData.setBackground(new Color(255, 255, 204));
		txtpnData.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtpnData.setEditable(false);
		scrollPane.setViewportView(txtpnData);

		lblSuraName = new JLabel("-");
		quranPanel.add(lblSuraName, BorderLayout.NORTH);
		lblSuraName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblSuraName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuraName
				.setFont(new Font(Constants.KFGQPC_fontFamily, Font.BOLD, 16));

		leftSplitPane = new JSplitPane();
		leftSplitPane.setOneTouchExpandable(true);
		leftSplitPane.setResizeWeight(0.5);
		splitPane.setLeftComponent(leftSplitPane);

		JPanel col1Panel = new JPanel();
		col1Panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftSplitPane.setRightComponent(col1Panel);
		col1Panel.setLayout(new BorderLayout(0, 0));

		slider_1 = new JSlider();
		slider_1.setSnapToTicks(true);
		slider_1.setValue(this.col1FontSize);
		slider_1.setMinorTickSpacing(2);
		slider_1.setMajorTickSpacing(4);
		slider_1.setMinimum(8);
		slider_1.setMaximum(32);
		col1Panel.add(slider_1, BorderLayout.SOUTH);
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// update the font size and reload the page
				if (!slider_1.getValueIsAdjusting()) {
					int size = slider_1.getValue();
					Main.this.col1FontSize = size;
					int _pagenum = ((Integer) Main.this.spinnerPage.getValue())
							.intValue();
					// TODO: Use SwingWorker thread
					Main.this.loadPage(1, _pagenum);
				}
			}
		});

		scrollPane_Trans1 = new JScrollPane();
		scrollPane_Trans1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col1Panel.add(scrollPane_Trans1);

		textPaneTrans1 = new JTextPane();
		textPaneTrans1.setEditable(false);
		textPaneTrans1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_Trans1.setViewportView(textPaneTrans1);

		transLabelpanel1 = new JPanel();
		col1Panel.add(transLabelpanel1, BorderLayout.NORTH);

		comboBoxTranslation1 = new JComboBox<QuranTranslationID>();
		comboBoxTranslation1
				.setModel(new DefaultComboBoxModel<QuranTranslationID>(
						QuranTranslationID.values()));
		comboBoxTranslation1.setSelectedIndex(0);
		comboBoxTranslation1.setFont(new Font("Hussaini Nastaleeq", Font.BOLD,
				12));
		comboBoxTranslation1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		comboBoxTranslation1.addActionListener(col1AL);
		transLabelpanel1.add(comboBoxTranslation1);

		JPanel col2Panel = new JPanel();
		col2Panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftSplitPane.setLeftComponent(col2Panel);
		col2Panel.setLayout(new BorderLayout(0, 0));

		slider_2 = new JSlider();
		slider_2.setValue(this.col2FontSize);
		slider_2.setSnapToTicks(true);
		slider_2.setMinorTickSpacing(2);
		slider_2.setMinimum(8);
		slider_2.setMaximum(32);
		slider_2.setMajorTickSpacing(4);
		col2Panel.add(slider_2, BorderLayout.SOUTH);
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// update the font size and reload the page
				if (!slider_2.getValueIsAdjusting()) {
					int size = slider_2.getValue();
					Main.this.col2FontSize = size;
					int _pagenum = ((Integer) Main.this.spinnerPage.getValue())
							.intValue();
					// TODO: Use SwingWorker thread
					Main.this.loadPage(2, _pagenum);
				}
			}
		});

		scrollPane_Trans2 = new JScrollPane();
		scrollPane_Trans2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col2Panel.add(scrollPane_Trans2);

		textPaneTrans2 = new JTextPane();
		textPaneTrans2.setEditable(false);
		textPaneTrans2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_Trans2.setViewportView(textPaneTrans2);

		transLabelpanel2 = new JPanel();
		col2Panel.add(transLabelpanel2, BorderLayout.NORTH);

		comboBoxTranslation2 = new JComboBox<QuranTranslationID>();
		comboBoxTranslation2
				.setModel(new DefaultComboBoxModel<QuranTranslationID>(
						QuranTranslationID.values()));
		comboBoxTranslation2.setSelectedIndex(1);
		comboBoxTranslation2.setFont(new Font("Hussaini Nastaleeq", Font.BOLD,
				12));
		comboBoxTranslation2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		comboBoxTranslation2.addActionListener(col2AL);
		transLabelpanel2.add(comboBoxTranslation2);

		lblChapterNumber = new JLabel("");
		lblChapterNumber
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblChapterNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblChapterNumber.setFont(new Font(Constants.KFGQPC_fontFamily,
				Font.BOLD, 16));
		topPanel.add(lblChapterNumber, BorderLayout.WEST);

		lblAyahSurah = new JLabel("[0:0]");
		lblAyahSurah
				.setFont(new Font("KFGQPC Uthman Taha Naskh", Font.BOLD, 16));
		topPanel.add(lblAyahSurah, BorderLayout.EAST);

		pageSpinnerPanel = new JPanel();
		pageSpinnerPanel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		lblPage = new JLabel(Constants.UrduPage);
		pageSpinnerPanel.add(lblPage);
		lblPage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblPage.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblPage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblPage.setFont(new Font(Constants.KFGQPC_fontFamily, Font.BOLD, 12));

		spinnerPage = new JSpinner();
		pageSpinnerPanel.add(spinnerPage);
		spinnerPage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinnerPage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				Integer pageNum = (Integer) spinnerPage.getModel().getValue();
				// TODO: SwingWorker thread
				// clear existing highlighting
				removeHighlights(Main.this.txtpnData);
				removeHighlights(Main.this.textPaneTrans1);
				removeHighlights(Main.this.textPaneTrans2);

				// load page
				loadPage(0, pageNum);
				loadPage(1, pageNum);
				loadPage(2, pageNum);

				highlightStartPageAyah();

			}
		});
		spinnerPage.setModel(new SpinnerNumberModel(1, 1, quranData.getPages()
				.getPageList().size(), 1));
		lblOf = new JLabel(quranData.getPages().getPageList().size() + " - ");
		pageSpinnerPanel.add(lblOf);
		lblOf.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblOf.setFont(Loader.getInstance().getQuranicFont(
				QuranicFonts.FONT_KFGQPC_TN));
		bottomPanel.setLayout(new BorderLayout(0, 0));
		bottomPanel.add(pageSpinnerPanel, BorderLayout.CENTER);
		/*
		 * sorrahSpinnerPanel = new JPanel(); sorrahSpinnerPanel
		 * .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 * bottomPanel.add(sorrahSpinnerPanel);
		 * 
		 * lblBottomSoorah = new JLabel("-"); lblBottomSoorah.setFont(new
		 * Font("KFGQPC Uthman Taha Naskh", Font.BOLD, 12)); lblBottomSoorah
		 * .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 * sorrahSpinnerPanel.add(lblBottomSoorah);
		 * 
		 * spinnerSoorah = new JSpinner();
		 * sorrahSpinnerPanel.add(spinnerSoorah); spinnerSoorah
		 * .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 * spinnerSoorah.addChangeListener(new ChangeListener() {
		 * 
		 * public void stateChanged(ChangeEvent e) { Integer surah = (Integer)
		 * spinnerSoorah.getValue();
		 * spinnerPage.setValue(quranData.getSuras().getSuraList() .get(surah -
		 * 1).getPage());
		 * 
		 * } }); spinnerSoorah.setModel(new SpinnerNumberModel(1, 1, 114, 1));
		 */
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(splitPane, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setJMenuBar(menuBar);

		btngrpMnQuranText = new ButtonGroup();
		mnText = new JMenu(Constants.MenuUrduQuranText);
		mnText.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		mnText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(mnText);

		rdbtnmntmUsmaniText = new JRadioButtonMenuItem(
				Constants.MenuUrduUsmaniText);
		mnText.add(rdbtnmntmUsmaniText);
		rdbtnmntmUsmaniText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Determine if current text type is already usmani,
				// if it is, then don't load it

				removeHighlights(Main.this.txtpnData);

				// TODO: Use SwingWorker thread
				Main.this.quranText[0] = Loader.getInstance().getQuranText(
						QuranTextType.USMANI_SPECIALTANWEEN);
				Main.this.loadPage(0, (Integer) spinnerPage.getModel()
						.getValue());

				highlightAya(highlightPos);
				Main.this.txtpnData.setCaretPosition(highlightPos);

			}
		});
		rdbtnmntmUsmaniText.setFont(new Font(
				Constants.hussaini_nastaleeq_fontFamily, Font.BOLD, 12));
		rdbtnmntmUsmaniText.setSelected(true);
		rdbtnmntmUsmaniText
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btngrpMnQuranText.add(rdbtnmntmUsmaniText);

		rdbtnmntmSimpleText = new JRadioButtonMenuItem(
				Constants.MenuUrduImaliText);
		mnText.add(rdbtnmntmSimpleText);
		rdbtnmntmSimpleText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Determine if current text type is already simple,
				// if it is, then don't load it

				removeHighlights(Main.this.txtpnData);

				// TODO: Use SwingWorker thread
				Main.this.quranText[0] = Loader.getInstance().getQuranText(
						QuranTextType.SIMPLE);
				Main.this.loadPage(0, (Integer) spinnerPage.getModel()
						.getValue());

				highlightAya(highlightPos);
				Main.this.txtpnData.setCaretPosition(highlightPos);
			}
		});
		rdbtnmntmSimpleText.setFont(new Font(
				Constants.hussaini_nastaleeq_fontFamily, Font.BOLD, 12));
		rdbtnmntmSimpleText
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btngrpMnQuranText.add(rdbtnmntmSimpleText);

		mnIndex = new JMenu(Constants.MenuUrduIndexText);
		mnIndex.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		mnIndex.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(mnIndex);

		mnSura = new JMenu(Constants.UrduSoorah);
		mnSura.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		mnSura.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnIndex.add(mnSura);

		mnPara = new JMenu(Constants.UrduChapter);
		mnPara.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		mnPara.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnIndex.add(mnPara);

		mnTranslation = new JMenu(Constants.UrduTranslation);
		mnTranslation
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnTranslation.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		menuBar.add(mnTranslation);

		menuTranslationCol1 = new JMenu(Constants.MenuUrduTranslationColumn1);
		menuTranslationCol1.setFont(new Font("Hussaini Nastaleeq", Font.BOLD,
				12));
		menuTranslationCol1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnTranslation.add(menuTranslationCol1);

		menuTranslationCol2 = new JMenu(Constants.MenuUrduTranslationColumn2);
		menuTranslationCol2.setFont(new Font("Hussaini Nastaleeq", Font.BOLD,
				12));
		menuTranslationCol2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnTranslation.add(menuTranslationCol2);

		menuBar.add(Box.createHorizontalGlue());

		mnInformation = new JMenu(Constants.MenuUrduMaloomat);
		mnInformation.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		mnInformation
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(mnInformation);

		mntmQuranProject = new JMenuItem("");
		mntmQuranProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationDialog dialog = new InformationDialog(Main.this,
						true);
				dialog.setLocationRelativeTo(Main.this);
				dialog.setVisible(true);
			}
		});
		mntmQuranProject.setFont(new Font(
				Constants.hussaini_nastaleeq_fontFamily, Font.BOLD, 12));
		mntmQuranProject
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mntmQuranProject.setText(Constants.UrduQuranProject);
		mnInformation.add(mntmQuranProject);

		postInit();

	}

	private void postInit() {
		this.ayaOffsets[0] = new HashMap<Point, Point>();
		this.ayaOffsets[1] = new HashMap<Point, Point>();
		this.ayaOffsets[2] = new HashMap<Point, Point>();

		initializeSubMenus();

		initQuranText();
		initializeStartPagesofChapters();

		loadPage(0, 1);
		loadPage(1, 1);
		loadPage(2, 1);

		highlightStartPageAyah();
	}

	private void highlightStartPageAyah() {
		// set highlight to start of page
		PageData page = quranData.getPages().getPageMap()
				.get(spinnerPage.getValue());
		int currentPageStartSura = page.getSura();
		int currentPageStartAya = currentPageStartSura == 1 ? 2 : page.getAya();

		Point p = Main.this.ayaOffsets[0].get(new Point(currentPageStartSura,
				currentPageStartAya));
		highlightAya(p.x);

	}

	/**
	 * Attempts to highlight ayah
	 * 
	 * @param pos
	 *            Cursor position
	 */
	private void highlightAya(int pos) {
		// determine which aya lies in this position
		for (Point ayaNum : this.ayaOffsets[0].keySet()) {
			Point p = this.ayaOffsets[0].get(ayaNum);
			if (pos >= p.x && pos <= p.y) {
				// we got the aya number
				Point p1 = this.ayaOffsets[1].get(ayaNum);
				Point p2 = this.ayaOffsets[2].get(ayaNum);

				this.lblAyahSurah.setText(" [" + ayaNum.x + ":" + ayaNum.y
						+ "] ");
				// clear existing highlighting
				removeHighlights(this.txtpnData);
				removeHighlights(this.textPaneTrans1);
				removeHighlights(this.textPaneTrans2);

				// now highlight using our custom highlighter
				Highlighter hilite = this.txtpnData.getHighlighter();
				int ayaEndNumChars = String.valueOf(ayaNum.y).length() + 2;
				try {
					hilite.addHighlight(p.x, p.y - ayaEndNumChars,
							myHighlightPainter);
				} catch (BadLocationException e) {
					System.err
							.println("Invalid location while attempt to set highlighter: "
									+ e.getMessage());
				}

				hilite = this.textPaneTrans1.getHighlighter();
				try {
					hilite.addHighlight(p1.x, p1.y - ayaEndNumChars,
							myHighlightPainter);
					this.textPaneTrans1.setCaretPosition(p1.y);
				} catch (BadLocationException e) {
					System.err
							.println("Invalid location while attempt to set highlighter: "
									+ e.getMessage());
				}

				hilite = this.textPaneTrans2.getHighlighter();
				try {
					hilite.addHighlight(p2.x, p2.y - ayaEndNumChars,
							myHighlightPainter);
					this.textPaneTrans2.setCaretPosition(p2.y);
				} catch (BadLocationException e) {
					System.err
							.println("Invalid location while attempt to set highlighter: "
									+ e.getMessage());
				}

				break;
			}
		}

	}

	// Removes only our private highlights
	private void removeHighlights(JTextComponent textComp) {
		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		for (int i = 0; i < hilites.length; i++) {
			if (hilites[i].getPainter() instanceof MyHighlightPainter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}

	private class MyHighlightPainter extends
			DefaultHighlighter.DefaultHighlightPainter {
		public MyHighlightPainter(Color color) {
			super(color);
		}
	}

	private void initQuranText() {
		// each array hold data for each column
		quranText[0] = Loader.getInstance().getQuranText(
				QuranTextType.USMANI_SPECIALTANWEEN);
		quranText[1] = Loader.getInstance().getQuranTranslation(
				QuranTranslationID.UR_MAUDUDI);
		quranText[2] = Loader.getInstance().getQuranTranslation(
				QuranTranslationID.UR_AHMED_RAZA_KHAN);

	}

	private void initializeSubMenus() {
		SurahMenuItemActionListener surahMenuActionListener = new SurahMenuItemActionListener();
		int i = 1;
		JMenu menu = new JMenu("1-9");
		menu.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnSura.add(menu);
		for (SuraData sura : quranData.getSuras().getSuraList()) {
			if (i % 10 == 0) {
				int nextIndex = i + 9;
				menu = new JMenu(String.valueOf(i) + "-"
						+ String.valueOf(nextIndex <= 114 ? nextIndex : 114));
				menu.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
						Font.BOLD, 12));
				menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				mnSura.add(menu);
			}
			JMenuItem menuItem = new JMenuItem(sura.getIndex() + " - "
					+ sura.getName());
			menuItem.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
					Font.BOLD, 12));
			menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			menuItem.setActionCommand(String.valueOf(sura.getIndex()));
			menuItem.addActionListener(surahMenuActionListener);
			menu.add(menuItem);
			i++;
		}

		// populate juz
		ChapterMenuItemActionListener chapterMenuItemActionListener = new ChapterMenuItemActionListener();
		menu = new JMenu("1-15");
		menu.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnPara.add(menu);
		for (i = 1; i <= 15; i++) {
			JMenuItem menuItem = new JMenuItem(Constants.UrduChapter + " " + i);
			menuItem.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
					Font.BOLD, 12));
			menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			menuItem.setActionCommand(String.valueOf(i));
			menuItem.addActionListener(chapterMenuItemActionListener);
			menu.add(menuItem);
		}

		menu = new JMenu("16-30");
		menu.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnPara.add(menu);
		for (i = 16; i <= 30; i++) {
			JMenuItem menuItem = new JMenuItem(Constants.UrduChapter + " " + i);
			menuItem.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
					Font.BOLD, 12));
			menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			menuItem.setActionCommand(String.valueOf(i));
			menuItem.addActionListener(chapterMenuItemActionListener);
			menu.add(menuItem);
		}

		for (QuranTranslationID translations : QuranTranslationID.values()) {
			JMenuItem menuItem1 = new JMenuItem(translations.toString());
			menuItem1.setFont(new Font("Hussaini Nastaleeq", Font.BOLD, 12));
			menuItem1
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			JMenuItem menuItem2 = new JMenuItem(translations.toString());
			menuItem2.setFont(new Font("Hussaini Nastaleeq", Font.BOLD, 12));
			menuItem2
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			menuTranslationCol1.add(menuItem1);
			menuTranslationCol2.add(menuItem2);
			menuItem1.setActionCommand(translations.name());
			menuItem2.setActionCommand(translations.name());
			menuItem1.addActionListener(col1AL);
			menuItem2.addActionListener(col2AL);
		}

	}

	/**
	 * ActionListener for column1 translation.
	 * 
	 * It is used both by menu item and combo box. When used by menu item, we
	 * update value in combo box, results calling same method again.
	 * 
	 * @author usman
	 * 
	 */
	private class TranslationCol1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			QuranTranslationID translation = null;
			if (e.getSource() instanceof JComboBox) {
				translation = (QuranTranslationID) comboBoxTranslation1
						.getSelectedItem();
			} else {
				comboBoxTranslation1.setSelectedItem(QuranTranslationID
						.valueOf(e.getActionCommand()));
				return;
			}

			// TODO: Move into SwingWorker thread
			removeHighlights(Main.this.textPaneTrans1);
			quranText[1] = Loader.getInstance()
					.getQuranTranslation(translation);
			loadPage(1, ((Integer) spinnerPage.getValue()).intValue());
			highlightAya(highlightPos);
		}

	}

	/**
	 * ActionListener for column2 translation
	 * 
	 * It is used both by menu item and combo box. When used by menu item, we
	 * update value in combo box, results calling same method again.
	 * 
	 * @author usman
	 * 
	 */
	private class TranslationCol2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			QuranTranslationID translation = null;
			if (e.getSource() instanceof JComboBox) {
				translation = (QuranTranslationID) comboBoxTranslation2
						.getSelectedItem();
			} else {
				comboBoxTranslation2.setSelectedItem(QuranTranslationID
						.valueOf(e.getActionCommand()));
				return;
			}

			// TODO: Move into SwingWorker thread
			removeHighlights(Main.this.textPaneTrans2);
			quranText[2] = Loader.getInstance()
					.getQuranTranslation(translation);
			loadPage(2, ((Integer) spinnerPage.getValue()).intValue());
			highlightAya(highlightPos);
		}
	}

	private class ChapterMenuItemActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				int chapter = Integer.parseInt(e.getActionCommand());
				spinnerPage.setValue(getPageOfChapter(chapter));
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(Main.this,
						"Invalid Action Command: " + e.getActionCommand(),
						"Invalid Menu Action for Chapter",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private class SurahMenuItemActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				// spinnerSoorah.setValue(new Integer(e.getActionCommand()));
				spinnerPage.setValue(quranData.getSuras().getSuraList()
						.get(new Integer(e.getActionCommand()).intValue() - 1)
						.getPage());
				//TODO: Highlight start of surah
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(Main.this,
						"Invalid Action Command: " + e.getActionCommand(),
						"Invalid Menu Action for Surahs",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * Core logic. Add ayas to be displayed on a page
	 * 
	 * @param page
	 * @return
	 */
	private void loadPage(int col, int pageNum) {
		clearAyaOffset(col);
		DefaultStyledDocument doc = initDocStyles(col);
		StringBuilder suraLabelText = new StringBuilder();
		StringBuilder suraTooltipText = new StringBuilder();
		suraTooltipText.append("<html>");

		if (pageNum <= 0 || pageNum > totalPages) {
			throw new IllegalArgumentException("Invalid Page Number ["
					+ pageNum + "] not in range [" + "1-" + totalPages + "]");
		}
		// StringBuilder pageContents = new StringBuilder();

		PageData page = quranData.getPages().getPageMap().get(pageNum);
		int currentPageStartSura = page.getSura();
		int currentPageStartAya = page.getAya();
		Sura currentPageSura = quranText[col].getSuraMap().get(
				currentPageStartSura);
		SuraData suraData = quranData.getSuras().getSuraList()
				.get(currentPageStartSura - 1);

		// which chapter number belong to this page?
		this.lblChapterNumber.setText(Constants.UrduChapter + " "
				+ String.valueOf(getChapterOfPage(page.getIndex())) + " ");

		// special handling for page 1 - Surah Fatiha
		if (pageNum == 1) {

			for (int ayaNumber = 1; ayaNumber <= currentPageSura.getAyaMap()
					.size(); ayaNumber++) {
				updateAyaText(col, doc, currentPageSura, ayaNumber);
			}
			addLineFeed(doc);

			suraLabelText.append(Constants.ArabicSoorah).append(" ")
					.append(currentPageSura.getName());
			suraTooltipText.append(getSurahTooltipText(suraData));

		} else if (pageNum < totalPages) {
			// logic for all other pages, except last page
			PageData nextPage = quranData.getPages().getPageMap()
					.get(pageNum + 1);
			int nextPageStartSura = nextPage.getSura();
			int nextPageStartAya = nextPage.getAya();

			// page contains text from same sura
			if (currentPageStartSura == nextPageStartSura) {
				suraLabelText.append(Constants.ArabicSoorah).append(" ")
						.append(currentPageSura.getName());
				suraTooltipText.append(getSurahTooltipText(suraData));

				for (int ayaNumber = currentPageStartAya; ayaNumber < nextPageStartAya; ayaNumber++) {
					updateAyaText(col, doc, currentPageSura, ayaNumber);
				}
			} else if (currentPageStartSura < nextPageStartSura) {
				// page either ends sura on this page, or contain multiple
				// suras.

				// add rest of ayas of current sura
				for (int ayaNumber = currentPageStartAya; ayaNumber <= currentPageSura
						.getAyaMap().size(); ayaNumber++) {
					updateAyaText(col, doc, currentPageSura, ayaNumber);
				}
				suraLabelText.append(Constants.ArabicSoorah).append(" ")
						.append(currentPageSura.getName());
				suraTooltipText.append(getSurahTooltipText(suraData));

				// add other suras on current page (if required)
				for (int suraNumber = currentPageStartSura + 1; suraNumber < nextPageStartSura; suraNumber++) {
					Sura otherSura = quranText[col].getSuraMap()
							.get(suraNumber);
					SuraData otherSuraData = quranData.getSuras().getSuraList()
							.get(suraNumber - 1);
					suraLabelText.append(Constants.ArabicComma)
							.append(Constants.ArabicSoorah).append(" ")
							.append(otherSura.getName());
					suraTooltipText.append(getSurahTooltipText(otherSuraData));

					for (int ayaNumber = 1; ayaNumber <= otherSura.getAyaMap()
							.size(); ayaNumber++) {
						if (ayaNumber == 1) {
							// start of Bismillah in middle of page
							addLineFeed(doc);
						}
						updateAyaText(col, doc, otherSura, ayaNumber);
					}
				}

				// does nextPageStartSura starts on this page?
				if (nextPageStartAya > 1) {
					Sura nextPageSura = quranText[col].getSuraMap().get(
							nextPageStartSura);
					SuraData nextPageSuraData = quranData.getSuras()
							.getSuraList().get(nextPageStartSura - 1);
					suraLabelText.append(Constants.ArabicComma)
							.append(Constants.ArabicSoorah).append(" ")
							.append(nextPageSura.getName());
					suraTooltipText
							.append(getSurahTooltipText(nextPageSuraData));

					for (int ayaNumber = 1; ayaNumber < nextPageStartAya; ayaNumber++) {
						if (ayaNumber == 1) {
							// start of Bismillah in middle of page
							addLineFeed(doc);
						}
						updateAyaText(col, doc, nextPageSura, ayaNumber);
					}
				}

			}

		} else {
			// logic for last page
			for (int i = currentPageStartSura; i <= 114; i++) {
				Sura lastPageSura = quranText[col].getSuraMap().get(i);
				SuraData lastPageSuraData = quranData.getSuras().getSuraList()
						.get(i - 1);
				suraLabelText.append(Constants.ArabicSoorah).append(" ")
						.append(lastPageSura.getName());
				suraTooltipText.append(getSurahTooltipText(lastPageSuraData));
				if (i != 114) {
					suraLabelText.append(Constants.ArabicComma);
				}
				for (int ayaNumber = 1; ayaNumber <= lastPageSura.getAyaMap()
						.size(); ayaNumber++) {
					if (i > currentPageStartSura && ayaNumber == 1) {
						// start of bismillah in middle of page
						addLineFeed(doc);
					}
					updateAyaText(col, doc, lastPageSura, ayaNumber);
				}
			}

		}
		if (col == 0) {
			this.txtpnData.setDocument(doc);
			this.txtpnData.setCaretPosition(0);
			this.lblSuraName.setText(suraLabelText.toString());
			suraTooltipText.append("</html>");
			this.lblSuraName.setToolTipText(suraTooltipText.toString());
		} else if (col == 1) {
			this.textPaneTrans1.setDocument(doc);
			this.textPaneTrans1.setCaretPosition(0);
		} else if (col == 2) {
			this.textPaneTrans2.setDocument(doc);
			this.textPaneTrans2.setCaretPosition(0);
		}
	}

	/**
	 * Clear ayaoffset at start of page load
	 */
	private void clearAyaOffset(int col) {
		this.ayaOffsets[col].clear();
	}

	private void addLineFeed(DefaultStyledDocument doc) {
		try {
			doc.insertString(doc.getLength(), "\n", doc.getStyle("BaseStyle"));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add aya text on JTextPane
	 * 
	 * @param sura
	 * @param i
	 */
	private void updateAyaText(int col, DefaultStyledDocument doc, Sura sura,
			int i) {
		Aya aya = sura.getAyaList().get(i - 1);
		int suraIndex = sura.getIndex();
		String bismillahText = aya.getBismillah();
		String ayaText = aya.getText();
		char ayaNumStart = '\ufd3f';
		char ayaNumEnd = '\ufd3e';
		try {
			// add Bismillah if required. Special handling required for sura
			// Fatiha(1) and Sura Toba (9)
			if (suraIndex != 9 && i == 1) {
				if (suraIndex == 1) {
					bismillahText = ayaText + ayaNumStart
							+ getArabicAyaNumbering(i) + ayaNumEnd;
				} else if (suraIndex != 1 && bismillahText == null) {
					// this case is valid for Urdu translations
					bismillahText = quranText[col].getSuraList().get(0)
							.getAyaList().get(0).getText();
				}
				int offset = doc.getLength();

				doc.insertString(offset, bismillahText + "\n",
						doc.getStyle("BismillahStyle"));

				doc.setParagraphAttributes(offset, doc.getLength() - offset,
						doc.getStyle("BismillahStyle"), false);

				// we wish to return from aya 1 (bismillah) for surah 1
				if (suraIndex == 1) {
					return;
				}
			}

			int startOffset = doc.getLength();
			// add actual Aya text
			doc.insertString(startOffset, ayaText + ayaNumStart
					+ getArabicAyaNumbering(i) + ayaNumEnd + " ",
					doc.getStyle("BaseStyle"));

			// calculate offsetting for this aya for highlighting purposes
			int endOffset = doc.getLength() - 1;

			this.ayaOffsets[col].put(new Point(suraIndex, i), new Point(
					startOffset, endOffset));

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private String getArabicAyaNumbering(int number) {
		String strNum = String.valueOf(number);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strNum.length(); i++) {
			sb.append(Character.toChars(Integer.parseInt(String.valueOf(strNum
					.charAt(i))) + 0x660));
		}
		return sb.toString();
	}

	/**
	 * Initialize various style which are used by main textpane. This method
	 * gets called on loading of each page via loadPage
	 */
	private DefaultStyledDocument initDocStyles(int col) {
		// base style. All ayas are displayed in this style
		Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		DefaultStyledDocument doc = null;
		Style base = null;
		if (col == 0) {
			this.txtpnData.setDocument(new DefaultStyledDocument());
			doc = new DefaultStyledDocument();
			base = doc.addStyle("BaseStyle", defaultStyle);
			StyleConstants.setFontFamily(base, this.col0FontFamily);
			StyleConstants.setFontSize(base, this.col0FontSize);
		} else if (col == 1) {
			this.textPaneTrans1.setDocument(new DefaultStyledDocument());
			doc = new DefaultStyledDocument();
			base = doc.addStyle("BaseStyle", defaultStyle);
			StyleConstants.setFontFamily(base, this.col1FontFamily);
			StyleConstants.setFontSize(base, this.col1FontSize);

		} else if (col == 2) {
			this.textPaneTrans2.setDocument(new DefaultStyledDocument());
			doc = new DefaultStyledDocument();
			base = doc.addStyle("BaseStyle", defaultStyle);
			StyleConstants.setFontFamily(base, this.col2FontFamily);
			StyleConstants.setFontSize(base, this.col2FontSize);

		}
		// style for Bismillah, same as base, except bold and centered
		Style bismillahStyle = doc.addStyle("BismillahStyle", base);
		StyleConstants.setBold(bismillahStyle, true);
		StyleConstants
				.setAlignment(bismillahStyle, StyleConstants.ALIGN_CENTER);
		return doc;

	}

	private void initializeStartPagesofChapters() {
		startPagesofChapters = new int[30];
		int i = 0;
		for (JuzData jd : quranData.getJuzs().getJuzList()) {
			PageData pd = quranData
					.getPages()
					.getSuraAyaPageMap()
					.get(String.valueOf(jd.getSura())
							+ String.valueOf(jd.getAya()));
			startPagesofChapters[i++] = pd.getIndex();
		}
		Arrays.sort(startPagesofChapters);
	}

	private int getChapterOfPage(int page) {
		for (int j = 29; j >= 0; j--) {
			if (page >= startPagesofChapters[j]) {
				return j + 1;
			}
		}
		return 0; // not meant to happen
	}

	private int getPageOfChapter(int chapter) {
		return startPagesofChapters[chapter - 1];
	}

	private String getSurahTooltipText(SuraData data) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div dir=rtl align=center><font face='")
				.append(Constants.KFGQPC_fontFamily).append("'>")
				.append(Constants.ArabicSoorah).append(' ')
				.append(data.getName()).append(' ').append('(')
				.append(data.getIndex()).append(')').append("</font></div>");
		sb.append("<div dir=rtl align=right><font face='")
				.append(Constants.KFGQPC_fontFamily).append("'>");
		sb.append(Constants.UrduAyatCount).append(" - ").append(data.getAyas())
				.append("<br>");
		sb.append(Constants.UrduRukuCount).append(" - ")
				.append(data.getRukus()).append("<br>");
		sb.append(Constants.UrduEraofSurah).append(" - ");
		if (data.getType().equals("Meccan")) {
			sb.append(Constants.UrduMekki);
		} else {
			sb.append(Constants.UrduMedni);
		}
		sb.append("</font></div><br>");
		sb.append("<hr>");
		return sb.toString();
	}

	public static void main(String a[]) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main m = new Main();
				m.setSize(1024, 768);
				m.setVisible(true);
				m.setLocationRelativeTo(null);
			}
		});
	}
}
