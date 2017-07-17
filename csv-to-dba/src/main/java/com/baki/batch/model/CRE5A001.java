package com.baki.batch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CRE5A001")
public class CRE5A001 extends EntityCommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String refct1;
	private String tec501;
	private String ctr801;
	private String str502;
	private String d25501;
	private String rub003;
	private String rub103;
	private String rub999;

	private Integer rub110;
	private Integer rub202;

	private String rub529;
	private String rub531;
	private String rub213;
	private String rub251;

	private Date dat101;
	private Date dat102;

	private String t50001;
	private String eur001;
	private String rib003;
	private String rub105;
	private String rub106;

	private Double eur105;

	@Column
	public String getRefct1() {
		return refct1;
	}

	public void setRefct1(String refct1) {
		this.refct1 = refct1;
	}

	@Column
	public String getTec501() {
		return tec501;
	}

	public void setTec501(String tec501) {
		this.tec501 = tec501;
	}

	@Column
	public String getCtr801() {
		return ctr801;
	}

	public void setCtr801(String ctr801) {
		this.ctr801 = ctr801;
	}

	@Column
	public String getStr502() {
		return str502;
	}

	public void setStr502(String str502) {
		this.str502 = str502;
	}

	@Column
	public String getD25501() {
		return d25501;
	}

	public void setD25501(String d25501) {
		this.d25501 = d25501;
	}

	@Column
	public String getRub003() {
		return rub003;
	}

	public void setRub003(String rub003) {
		this.rub003 = rub003;
	}

	@Column
	public String getRub103() {
		return rub103;
	}

	public void setRub103(String rub103) {
		this.rub103 = rub103;
	}

	@Column
	public String getRub999() {
		return rub999;
	}

	public void setRub999(String rub999) {
		this.rub999 = rub999;
	}

	@Column
	public Integer getRub110() {
		return rub110;
	}

	public void setRub110(Integer rub110) {
		this.rub110 = rub110;
	}

	@Column
	public Integer getRub202() {
		return rub202;
	}

	public void setRub202(Integer rub202) {
		this.rub202 = rub202;
	}

	@Column
	public String getRub529() {
		return rub529;
	}

	public void setRub529(String rub529) {
		this.rub529 = rub529;
	}

	@Column
	public String getRub531() {
		return rub531;
	}

	public void setRub531(String rub531) {
		this.rub531 = rub531;
	}

	@Column
	public String getRub213() {
		return rub213;
	}

	public void setRub213(String rub213) {
		this.rub213 = rub213;
	}

	@Column
	public String getRub251() {
		return rub251;
	}

	public void setRub251(String rub251) {
		this.rub251 = rub251;
	}

	@Column
	public Date getDat101() {
		return dat101;
	}

	public void setDat101(Date dat101) {
		this.dat101 = dat101;
	}

	@Column
	public Date getDat102() {
		return dat102;
	}

	public void setDat102(Date dat102) {
		this.dat102 = dat102;
	}

	@Column
	public String getT50001() {
		return t50001;
	}

	public void setT50001(String t50001) {
		this.t50001 = t50001;
	}

	@Column
	public String getEur001() {
		return eur001;
	}

	public void setEur001(String eur001) {
		this.eur001 = eur001;
	}

	@Column
	public String getRib003() {
		return rib003;
	}

	public void setRib003(String rib003) {
		this.rib003 = rib003;
	}

	@Column
	public String getRub105() {
		return rub105;
	}

	public void setRub105(String rub105) {
		this.rub105 = rub105;
	}

	@Column
	public String getRub106() {
		return rub106;
	}

	public void setRub106(String rub106) {
		this.rub106 = rub106;
	}

	@Column
	public Double getEur105() {
		return eur105;
	}

	public void setEur105(Double eur105) {
		this.eur105 = eur105;
	}

}
