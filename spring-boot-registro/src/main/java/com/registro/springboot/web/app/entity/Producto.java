package com.registro.springboot.web.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="productos")
public class Producto  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String codigoProduct;
	private String nombre;
	private double precio;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;
	private String foto;
	
	public String getCodigoProduct() {
		return codigoProduct;
	}
	public void setCodigoProduct(String codigoProduct) {
		this.codigoProduct = codigoProduct;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
