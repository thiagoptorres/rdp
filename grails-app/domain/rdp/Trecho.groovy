package rdp

class Trecho {
	
	String tipo
	String origem
	String destino
	Date   data
	String turno
  //  DateTime birthdate
  
	static belongsTo = [ requisicao : Requisicao ]
  
	static constraints = {
	  requisicao null: false, blank: false
	  tipo inList: ["AEREO", "TERRESTRE"]
	  origem size: 4..20, blank: false
	  destino size: 4..20, blank: false
	  data blank: false
	  turno inList: ["MANHÃ", "TARDE", "NOITE"]
	}
  
	String toString() {
		return tipo + ': ' + origem + ' > ' + destino + ' - ' + data.format("dd/MM/yyyy") + ' - ' + turno 
	}
 }
