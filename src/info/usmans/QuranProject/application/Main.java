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
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
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
import java.awt.GridLayout;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -735356187246028596L;
	private Quran[] quranText = new Quran[3];
	private QuranData quranData = Loader.getInstance().getQuranData();
	private int totalPages = quranData.getPages().getPageList().size();

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
	private JPanel sorrahSpinnerPanel;
	private JLabel lblBottomSoorah;
	private JSpinner spinnerSoorah;
	private JLabel lblTranslationCol1;
	private JLabel lblTranslationCol2;

	public Main() {
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
		splitPane.setResizeWeight(0.65);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				splitPane,
																				GroupLayout.DEFAULT_SIZE,
																				668,
																				Short.MAX_VALUE))
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				bottomPanel,
																				GroupLayout.DEFAULT_SIZE,
																				668,
																				Short.MAX_VALUE))
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addGap(12)
																		.addComponent(
																				topPanel,
																				GroupLayout.DEFAULT_SIZE,
																				668,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(topPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 527,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		txtpnData = new JTextPane();
		txtpnData.setBackground(new Color(255, 255, 204));
		txtpnData.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtpnData.setEditable(false);
		scrollPane.setViewportView(txtpnData);

		leftSplitPane = new JSplitPane();
		leftSplitPane.setOneTouchExpandable(true);
		leftSplitPane.setResizeWeight(0.5);
		splitPane.setLeftComponent(leftSplitPane);

		JPanel col1Panel = new JPanel();
		col1Panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftSplitPane.setRightComponent(col1Panel);
		col1Panel.setLayout(new BorderLayout(0, 0));

		scrollPane_Trans1 = new JScrollPane();
		scrollPane_Trans1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col1Panel.add(scrollPane_Trans1);

		textPaneTrans1 = new JTextPane();
		textPaneTrans1.setEditable(false);
		textPaneTrans1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_Trans1.setViewportView(textPaneTrans1);

		lblTranslationCol1 = new JLabel("");
		lblTranslationCol1
				.setFont(new Font("Hussaini Nastaleeq", Font.BOLD, 12));
		lblTranslationCol1
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col1Panel.add(lblTranslationCol1, BorderLayout.NORTH);

		JPanel col2Panel = new JPanel();
		col2Panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftSplitPane.setLeftComponent(col2Panel);
		col2Panel.setLayout(new BorderLayout(0, 0));

		scrollPane_Trans2 = new JScrollPane();
		scrollPane_Trans2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col2Panel.add(scrollPane_Trans2);

		textPaneTrans2 = new JTextPane();
		textPaneTrans2.setEditable(false);
		textPaneTrans2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_Trans2.setViewportView(textPaneTrans2);

		lblTranslationCol2 = new JLabel("");
		lblTranslationCol2
				.setFont(new Font("Hussaini Nastaleeq", Font.BOLD, 12));
		lblTranslationCol2
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		col2Panel.add(lblTranslationCol2, BorderLayout.NORTH);

		lblSuraName = new JLabel("");
		lblSuraName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblSuraName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuraName
				.setFont(new Font(Constants.KFGQPC_fontFamily, Font.BOLD, 16));
		topPanel.add(lblSuraName, BorderLayout.CENTER);

		lblChapterNumber = new JLabel("");
		lblChapterNumber
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblChapterNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblChapterNumber.setFont(new Font(Constants.KFGQPC_fontFamily,
				Font.BOLD, 16));
		topPanel.add(lblChapterNumber, BorderLayout.EAST);

		bottomPanel.setLayout(new GridLayout(0, 4, 0, 0));
		// bottomPanel.add(spinner);

		pageSpinnerPanel = new JPanel();
		pageSpinnerPanel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bottomPanel.add(pageSpinnerPanel);

		lblPage = new JLabel(Constants.UrduPage);
		pageSpinnerPanel.add(lblPage);
		lblPage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblPage.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblPage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblPage.setFont(new Font(Constants.hussaini_nastaleeq_fontFamily,
				Font.BOLD, 12));

		spinnerPage = getCustomSpinner();
		pageSpinnerPanel.add(spinnerPage);
		spinnerPage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinnerPage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				Object obj = spinnerPage.getModel().getValue();
				if (obj instanceof Integer) {
					// load page
					loadPage(0, (Integer) obj);
					loadPage(1, (Integer) obj);
					loadPage(2, (Integer) obj);
				}
			}
		});
		spinnerPage.setModel(new SpinnerNumberModel(1, 1, quranData.getPages()
				.getPageList().size(), 1));

		lblOf = new JLabel(quranData.getPages().getPageList().size() + " - ");
		pageSpinnerPanel.add(lblOf);
		lblOf.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblOf.setFont(Loader.getInstance().getQuranicFont(
				QuranicFonts.FONT_HUSSAINI_NASTALEEQ));

		sorrahSpinnerPanel = new JPanel();
		sorrahSpinnerPanel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bottomPanel.add(sorrahSpinnerPanel);

		lblBottomSoorah = new JLabel(Constants.UrduSoorah);
		lblBottomSoorah.setFont(new Font("Hussaini Nastaleeq", Font.BOLD, 12));
		lblBottomSoorah
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sorrahSpinnerPanel.add(lblBottomSoorah);

		spinnerSoorah = new JSpinner();
		// $hide>>$
		spinnerSoorah = getCustomSpinner();
		// $hide<<$
		sorrahSpinnerPanel.add(spinnerSoorah);
		spinnerSoorah
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinnerSoorah.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				Integer surah = (Integer) spinnerSoorah.getValue();
				spinnerPage.setValue(quranData.getSuras().getSuraList()
						.get(surah - 1).getPage());

			}
		});
		spinnerSoorah.setModel(new SpinnerNumberModel(1, 1, 114, 1));
		getContentPane().setLayout(groupLayout);

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
				Main.this.quranText[0] = Loader.getInstance().getQuranText(
						QuranTextType.USMANI_SPECIALTANWEEN);
				Main.this.loadPage(0, (Integer) spinnerPage.getModel()
						.getValue());

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
				Main.this.quranText[0] = Loader.getInstance().getQuranText(
						QuranTextType.SIMPLE);
				Main.this.loadPage(0, (Integer) spinnerPage.getModel()
						.getValue());
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

		initializeSubMenus();

		// non GUI Initialization
		initQuranText();
		initializeStartPagesofChapters();

		loadPage(0, 1);
		loadPage(1, 1);
		loadPage(2, 1);

	}

	private void initQuranText() {
		// each array hold data for each column
		quranText[0] = Loader.getInstance().getQuranText(
				QuranTextType.USMANI_SPECIALTANWEEN);
		quranText[1] = Loader.getInstance().getQuranTranslation(
				QuranTranslationID.UR_MAUDUDI);
		quranText[2] = Loader.getInstance().getQuranTranslation(
				QuranTranslationID.UR_AHMED_RAZA_KHAN);

		// update default display of translation author labels
		lblTranslationCol1.setText(Constants.UrduTranslationBy + " "
				+ QuranTranslationID.UR_MAUDUDI.toString());
		lblTranslationCol2.setText(Constants.UrduTranslationBy + " "
				+ QuranTranslationID.UR_AHMED_RAZA_KHAN.toString());

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

		// populate translations
		TranslationCol1 col1AL = new TranslationCol1();
		TranslationCol2 col2AL = new TranslationCol2();
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
	 * ActionListener for column1 translation
	 * 
	 * @author usman
	 * 
	 */
	private class TranslationCol1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String transValue = e.getActionCommand();
			QuranTranslationID translation = QuranTranslationID
					.valueOf(transValue);
			quranText[1] = Loader.getInstance()
					.getQuranTranslation(translation);
			loadPage(1, ((Integer) spinnerPage.getValue()).intValue());
			lblTranslationCol1.setText(Constants.UrduTranslationBy + " "
					+ translation.toString());

		}

	}

	/**
	 * ActionListener for column2 translation
	 * 
	 * @author usman
	 * 
	 */
	private class TranslationCol2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String transValue = e.getActionCommand();
			QuranTranslationID translation = QuranTranslationID
					.valueOf(transValue);
			quranText[2] = Loader.getInstance()
					.getQuranTranslation(translation);
			loadPage(2, ((Integer) spinnerPage.getValue()).intValue());
			lblTranslationCol2.setText(Constants.UrduTranslationBy + " "
					+ translation.toString());
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
				spinnerSoorah.setValue(new Integer(e.getActionCommand()));
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(Main.this,
						"Invalid Action Command: " + e.getActionCommand(),
						"Invalid Menu Action for Surahs",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private JSpinner getCustomSpinner() {
		return new JSpinner() {

			private static final long serialVersionUID = -4948001062221019002L;

			@Override
			public void setLayout(LayoutManager mgr) {
				// $hide>>$
				super.setLayout(new BorderLayout() {
					private static final long serialVersionUID = 1453458704791041504L;

					@Override
					public void addLayoutComponent(Component comp,
							Object constraints) {
						if ("Editor".equals(constraints)) {
							constraints = "Center";
						} else if ("Next".equals(constraints)) {
							constraints = "West";
						} else if ("Previous".equals(constraints)) {
							constraints = "East";
						}
						super.addLayoutComponent(comp, constraints);
					}
				});
				// $hide<<$
			}
		};
	}

	/**
	 * Add ayas to be displayed on a page
	 * 
	 * @param page
	 * @return
	 */
	private void loadPage(int col, int pageNum) {
		DefaultStyledDocument doc = initDocStyles(col);
		StringBuilder suraLabelText = new StringBuilder();
		StringBuilder suraInformationText = new StringBuilder();
		suraInformationText.append("<html>");

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
				+ String.valueOf(getChapterOfPage(page.getIndex())));

		// special handling for page 1 - Surah Fatiha
		if (pageNum == 1) {

			for (int ayaNumber = 1; ayaNumber <= currentPageSura.getAyaMap()
					.size(); ayaNumber++) {
				updateAyaText(col, doc, currentPageSura, ayaNumber);
			}
			addLineFeed(doc);

			suraLabelText.append(Constants.ArabicSoorah).append(" ")
					.append(currentPageSura.getName());
			// TODO: Use Urdu text
			suraInformationText.append("<div dir=rtl align=center>")
					.append(suraData.getName()).append("</div><br>");
			suraInformationText.append("Number of Aya: ")
					.append(suraData.getAyas()).append("<br>");
			suraInformationText.append("Number of Rukus: ")
					.append(suraData.getRukus()).append("<br>");
			suraInformationText.append("Type: ").append(suraData.getType())
					.append("<br>");
			suraInformationText.append("Revealing Order: "
					+ suraData.getOrder());

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
				suraInformationText.append("<div dir=rtl align=center>")
						.append(suraData.getName()).append("</div><br>");
				suraInformationText.append("Number of Aya: ")
						.append(suraData.getAyas()).append("<br>");
				suraInformationText.append("Number of Rukus: ")
						.append(suraData.getRukus()).append("<br>");
				suraInformationText.append("Type: ").append(suraData.getType())
						.append("<br>");
				suraInformationText.append(
						"Revealing Order: " + suraData.getOrder()).append(
						"<hr>");
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
				suraInformationText.append("<div dir=rtl align=center>")
						.append(suraData.getName()).append("</div><br>");
				suraInformationText.append("Number of Aya: ")
						.append(suraData.getAyas()).append("<br>");
				suraInformationText.append("Number of Rukus: ")
						.append(suraData.getRukus()).append("<br>");
				suraInformationText.append("Type: ").append(suraData.getType())
						.append("<br>");
				suraInformationText.append(
						"Revealing Order: " + suraData.getOrder()).append(
						"<hr>");

				// add other suras on current page (if required)
				for (int suraNumber = currentPageStartSura + 1; suraNumber < nextPageStartSura; suraNumber++) {
					Sura otherSura = quranText[col].getSuraMap()
							.get(suraNumber);
					SuraData otherSuraData = quranData.getSuras().getSuraList()
							.get(suraNumber - 1);
					suraLabelText.append(Constants.ArabicComma)
							.append(Constants.ArabicSoorah).append(" ")
							.append(otherSura.getName());
					suraInformationText.append("<div dir=rtl align=center>")
							.append(otherSuraData.getName())
							.append("</div><br>");
					suraInformationText.append("Number of Aya: ")
							.append(otherSuraData.getAyas()).append("<br>");
					suraInformationText.append("Number of Rukus: ")
							.append(otherSuraData.getRukus()).append("<br>");
					suraInformationText.append("Type: ")
							.append(otherSuraData.getType()).append("<br>");
					suraInformationText.append(
							"Revealing Order: " + otherSuraData.getOrder())
							.append("<hr>");

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
					suraInformationText.append("<div dir=rtl align=center>")
							.append(nextPageSuraData.getName())
							.append("</div><br>");
					suraInformationText.append("Number of Aya: ")
							.append(nextPageSuraData.getAyas()).append("<br>");
					suraInformationText.append("Number of Rukus: ")
							.append(nextPageSuraData.getRukus()).append("<br>");
					suraInformationText.append("Type: ")
							.append(nextPageSuraData.getType()).append("<br>");
					suraInformationText.append(
							"Revealing Order: " + nextPageSuraData.getOrder())
							.append("<hr>");

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
				suraInformationText.append("<div dir=rtl align=center>")
						.append(lastPageSuraData.getName())
						.append("</div><br>");
				suraInformationText.append("Number of Aya: ")
						.append(lastPageSuraData.getAyas()).append("<br>");
				suraInformationText.append("Number of Rukus: ")
						.append(lastPageSuraData.getRukus()).append("<br>");
				suraInformationText.append("Type: ")
						.append(lastPageSuraData.getType()).append("<br>");
				suraInformationText.append(
						"Revealing Order: " + lastPageSuraData.getOrder())
						.append("<hr>");
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
			suraInformationText.append("</html>");
			this.lblSuraName.setToolTipText(suraInformationText.toString());
		} else if (col == 1) {
			this.textPaneTrans1.setDocument(doc);
			this.textPaneTrans1.setCaretPosition(0);
		} else if (col == 2) {
			this.textPaneTrans2.setDocument(doc);
			this.textPaneTrans2.setCaretPosition(0);
		}
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
					bismillahText = bismillahText + ayaNumStart
							+ getArabicAyaNumbering(i) + ayaNumEnd;
				}
				int offset = doc.getLength();

				doc.insertString(offset, bismillahText + "\n",
						doc.getStyle("BismillahStyle"));

				doc.setParagraphAttributes(offset, doc.getLength() - offset,
						doc.getStyle("BismillahStyle"), false);

				if (suraIndex == 1) {
					return;
				}
			}

			// add actual Aya text
			doc.insertString(doc.getLength(), ayaText + ayaNumStart
					+ getArabicAyaNumbering(i) + ayaNumEnd + " ",
					doc.getStyle("BaseStyle"));

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
			StyleConstants.setFontFamily(base, Constants.me_quran_FontFamily);
			StyleConstants.setFontSize(base, 24);
		} else if (col == 1) {
			this.textPaneTrans1.setDocument(new DefaultStyledDocument());
			doc = new DefaultStyledDocument();
			base = doc.addStyle("BaseStyle", defaultStyle);
			StyleConstants.setFontFamily(base, Constants.KFGQPC_fontFamily);
			StyleConstants.setFontSize(base, 20);

		} else if (col == 2) {
			this.textPaneTrans2.setDocument(new DefaultStyledDocument());
			doc = new DefaultStyledDocument();
			base = doc.addStyle("BaseStyle", defaultStyle);
			StyleConstants.setFontFamily(base, Constants.KFGQPC_fontFamily);
			StyleConstants.setFontSize(base, 20);

		}
		// StyleConstants.setAlignment(base, StyleConstants.ALIGN_JUSTIFIED);
		// style for Bismillah
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
				// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				// m.setLocation(dim.width/2-m.getSize().width/2,
				// dim.height/2-m.getSize().height/2);
				m.setLocationRelativeTo(null);
			}
		});
	}
}
