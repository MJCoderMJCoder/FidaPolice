/**
 * 
 */
package com.lzf.fida.bean;

/**
 * @author MJCoder
 * 
 *         实体类：日常工作列表（ListView-rountine）
 *
 */
public class Rountine {
	private int image; // 图标
	private String chinese; // 中文提示

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
