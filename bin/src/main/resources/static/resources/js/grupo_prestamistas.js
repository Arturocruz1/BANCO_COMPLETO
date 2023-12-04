const $tableIntegrantesLibres = $('#tableListaPrestamista');
const $tableIntegrantesGrupo = $('#tableIntegrantes');

function agregarIntegrante(codigoPrestamista) {
	const codigoLider = $("#usuarioLiderId").val();

	$.post(`/GrupoPrestamistas/integrantes/add?codigoJefe=${codigoLider}&codigoIntegrante=${codigoPrestamista}`, function (data, status) {
		alert("Se agregó correctamente!!");
		$tableIntegrantesLibres.bootstrapTable('refresh');
		$tableIntegrantesGrupo.bootstrapTable("refresh");
	});

}

function operateFormatter(value, row, index) {
	console.log("operateFormatter", value, row, index);
	const usuarioId = row.codigoUsuario;

	return `
			<a class="btn btn-success btn-agregar-grupo" onClick="agregarIntegrante(${usuarioId})">
				<i class="bi bi-person-plus-fill"></i>
			</a>
			`;
}

function initTables() {
	$tableIntegrantesLibres.bootstrapTable('destroy').bootstrapTable({
		locale: "es-ES",
		columns: [
			{
				title: 'Item ID',
				field: 'codigoUsuario',
				rowspan: 1,
				align: 'center',
				valign: 'middle',
				sortable: true,
			}, {
				title: "Usuario",
				field: "username",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Ape. Paterno",
				field: "paternoUsuario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Ape. Materno",
				field: "marternoUsario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Correo",
				field: "emailUsuario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Fecha de nacimiento",
				field: "fechaNacimiento",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				field: 'operate',
				title: 'Acciones',
				align: 'center',
				clickToSelect: false,
				events: window.operateEvents,
				formatter: operateFormatter
			}
		]
	});


	//tabla de integrantes
	$tableIntegrantesGrupo.bootstrapTable('destroy').bootstrapTable({
		locale: "es-ES",
		columns: [
			{
				title: 'Item ID',
				field: 'codigoUsuario',
				rowspan: 1,
				align: 'center',
				valign: 'middle',
				sortable: true,
			}, {
				title: "Usuario",
				field: "username",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Ape. Paterno",
				field: "paternoUsuario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Ape. Materno",
				field: "marternoUsario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Correo",
				field: "emailUsuario",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}, {
				title: "Fecha de nacimiento",
				field: "fechaNacimiento",
				rowspan: 1,
				align: "center",
				valign: "middle",
				sortable: true,
			}
		]
	});

}

$(function () {
	initTables()

})
