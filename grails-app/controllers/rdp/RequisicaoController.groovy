package rdp

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
//import org.xhtmlrenderer.simple.PDFRenderer
//import org.xhtmlrenderer.simple.XHTMLPanel
//import org.xhtmlrenderer.simple.ImageRenderer

@Transactional(readOnly = true)
class RequisicaoController {

 	// Export service provided by Export plugin
	def exportService
	def grailsApplication  //inject GrailsApplication

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def xml = {
		response.setHeader('Content-Type', 'text/html')
		List fields = ["nome", "matricula","trechos"]
		Map labels = ["nome": "Nome", "matricula": "Matricula"]
		exportService.export("xml", response.outputStream,Requisicao.findAllByMatricula(params.id), fields,labels,[:],[:])
	}
			
	/*
	def toPdf = {
		def baseUri = request.scheme + "://" + request.serverName + ":" + request.serverPort + grailsAttributes.getApplicationUri(request)
	 
		 // Rendering View
		 def requisicao = Requisicao.get(params.id)
		 def render_result = g.render(template:"/requisicao/pdf.gsp", model: [requisicao: requisicao])
	 
		 // creating PDF
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();

		 ITextRenderer renderer = new ITextRenderer();
		 byte[] b
		 renderer.setDocumentFromString(render_result.toString(), baseUri);
		 renderer.layout();
		 renderer.createPDF(baos);
		 b = baos.toByteArray();
	 
		 // Sending file to the user
		 response.setContentType("application/pdf")
		 response.setHeader("Content-disposition", "attachment; filename=${requisicao.title}.pdf")
		 response.setContentLength(b.length)
		 response.getOutputStream().write(b)
	 } */ 
	
	def pdfRenderingService
	
	def renderFormPDF(){
		def formInstance = Requisicao.get(params.id)
		def args = [template:"pdf", model:[form:formInstance]]
		pdfRenderingService.render(args+[controller:this],response)
	}
	
	def pdf(Requisicao requisicaoInstance){
		def requisicao = Requisicao.get(params.id)
		//renderPdf(template: "pdf", model: [requisicao: Requisicao.list()], filename: "file.pdf")
		renderPdf(template: "pdf", model: [requisicao: requisicao], filename: "file.pdf")
	}
	
	def teste(){
		//render "Hello World"
		
		//def xmlText = '<?xml version="1.0"?>' +
		//'<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" ' +
		//'soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding"> ' +
		//'</soap:Envelope>'
		//render(text: xmlText, contentType: "text/xml", encoding: "UTF-8")

		render(view: "_pdf", model: [name:"John Doe"])
	}
	
	
	

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Requisicao.list(params), model:[requisicaoInstanceCount: Requisicao.count()]
    }

    def show(Requisicao requisicaoInstance) {
        respond requisicaoInstance
    }

    def create() {
        respond new Requisicao(params)
    }

    @Transactional
    def save(Requisicao requisicaoInstance) {
        if (requisicaoInstance == null) {
            notFound()
            return
        }

        if (requisicaoInstance.hasErrors()) {
            respond requisicaoInstance.errors, view:'create'
            return
        }

        requisicaoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requisicao.label', default: 'Requisicao'), requisicaoInstance.id])
                redirect requisicaoInstance
            }
            '*' { respond requisicaoInstance, [status: CREATED] }
        }
    }

    def edit(Requisicao requisicaoInstance) {
        respond requisicaoInstance
    }

    @Transactional
    def update(Requisicao requisicaoInstance) {
        if (requisicaoInstance == null) {
            notFound()
            return
        }

        if (requisicaoInstance.hasErrors()) {
            respond requisicaoInstance.errors, view:'edit'
            return
        }

        requisicaoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Requisicao.label', default: 'Requisicao'), requisicaoInstance.id])
                redirect requisicaoInstance
            }
            '*'{ respond requisicaoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Requisicao requisicaoInstance) {

        if (requisicaoInstance == null) {
            notFound()
            return
        }

        requisicaoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Requisicao.label', default: 'Requisicao'), requisicaoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requisicao.label', default: 'Requisicao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}