/**
 * 
 */
package com.lzf.fida.bean;

/**
 * @author MJCoder
 * 
 *         ʵ���ࣺ�ճ������б�ListView-rountine��
 *
 */
public class Rountine {
	private int image; // ͼ��
	private String chinese; // ������ʾ

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public Rountine(int image, String chinese) {
		super();
		this.image = image;
		this.chinese = chinese;
	}

	public Rountine() {
		super();
	}

}
