package edu.nsu.library.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nsu.library.bean.Suggest;
import edu.nsu.library.bean.User;
import edu.nsu.library.dao.SuggestDAO;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class SuggestionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public SuggestionDialog(int id) {
		userId = id;
		setBounds(100, 100, 637, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 73, 494, 202);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u7559\u8A00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(211, 10, 182, 41);
		contentPanel.add(lblNewLabel);
		
		btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertSuggest();
				JOptionPane.showMessageDialog
				(null, "留言成功！","正确信息",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(226, 294, 109, 31);
		contentPanel.add(btnNewButton);
	}
	private void insertSuggest(){
		SuggestDAO suggestDAO = new SuggestDAO();
		Suggest suggest = new Suggest();
		suggest.setContent(textArea.getText().trim());
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(now);
		suggest.setSuggestTime(str);
		suggest.setUserid(getUserId());
		try {
			suggestDAO.addSuggest(suggest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
