<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<body>
<h1>Requisição de Diária e Passagem [ Nº ${requisicao?.id} ]</h1>

<table cellpadding="5" >
<tr class="even"><td bgcolor="#E5E5E5"><b>Beneficiário</b></td><td bgcolor="#E5E5E5"><b></b></td></tr>
<tr class="even"><td>Tipo:</td><td><b>${requisicao?.beneficiario}</b></td></tr>
<tr class="even"><td>Matrícula/Nome:</td><td><b>${requisicao?.matricula} - ${requisicao?.nome}</b></td></tr>
<tr class="even"><td>Cargo:</td><td><b>${requisicao?.cargo}</b></td></tr>
<tr class="even"><td>Função:</td><td><b>${requisicao?.funcao}</b></td></tr>
<tr class="even"><td>CPF:</td><td><b>${requisicao?.cpf}</b></td></tr>
<tr class="even"><td>RG:</td><td><b>${requisicao?.rg}</b></td></tr>
<tr class="even"><td>eMail:</td><td><b>${requisicao?.email}</b></td></tr>
<tr class="even"><td>Tel Fixo:</td><td><b>${requisicao?.tel_fixo}</b></td></tr>
<tr class="even"><td>Tel Celular:</td><td><b>${requisicao?.tel_celular}</b></td></tr>
<tr class="even"><td bgcolor="#E5E5E5"><b>Dados Bancários</b></td><td bgcolor="#E5E5E5"><b></b></td></tr>
<tr class="even"><td>Nome do Banco:</td><td><b>${requisicao?.banco_nome}</b></td></tr>
<tr class="even"><td>Número do Banco:</td><td><b>${requisicao?.banco_numero}</b></td></tr>
<tr class="even"><td>Agência:</td><td><b>${requisicao?.banco_agencia}</b></td></tr>
<tr class="even"><td>Conta:</td><td><b>${requisicao?.banco_conta}</b></td></tr>
<tr class="even"><td bgcolor="#E5E5E5"><b>Dados da Viagem</b></td><td bgcolor="#E5E5E5"><b></b></td></tr>
<tr class="even"><td>Objetivo da Viagem:</td><td><b>${requisicao?.viagem_objetivo}</b></td></tr>
<tr class="even"><td>Data Inicio do Afastamento:</td><td><b><g:formatDate format="dd/MM/yyyy" date="${requisicao?.afastamento_data_inicio}"/></b></td></tr>
<tr class="even"><td>Data final do Afastamento:</td><td><b><g:formatDate format="dd/MM/yyyy" date="${requisicao?.afastamento_data_termino}"/></b></td></tr>
<tr class="even"><td>Data/Hora Inicio do Trabalho:</td><td><b><g:formatDate format="dd/MM/yyyy" date="${requisicao?.trabalho_data_inicio}"/>&nbsp;&nbsp;${requisicao?.trabalho_hora_inicio}</b></td></tr>
<tr class="even"><td>Data/Hora Término do Trabalho:</td><td><b><g:formatDate format="dd/MM/yyyy" date="${requisicao?.trabalho_data_termino}"/>&nbsp;&nbsp;${requisicao?.trabalho_hora_termino}</b></td></tr>
<tr class="even"><td>Tipo Deslocamento:</td><td><b>${requisicao?.deslocamento_tipo}</b></td></tr>
<tr class="even"><td>Tipo Custeio:</td><td><b>${requisicao?.custeio_tipo}</b></td></tr>
<tr class="even"><td>Justificativa:</td><td><b>${requisicao?.justificativa}</b></td></tr>
<tr class="even"><td>Trechos:</td><td><b></b></td></tr>
<g:each in="${requisicao?.trechos}" var="t">
	<tr class="even"><td>${t.id}</td><td><b><g:link controller="trecho" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></b></td></tr>
</g:each>

</table>

</body>
</html>