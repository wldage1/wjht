$(function() {	
		var area;
		var province;
	 	var city;
	 	var District;	 	
	 	$.ajax({
			url : path+'/organization/grid.json',
			data : {
				level : 1
			},
			async : false,
			success : function(result) {
				if (result.success == "success") {
					var data = result.data;
					area = data;				
				}
			}
		});	
	 	$.ajax({
			url : path+'/organization/grid.json',
			data : {
				level : 2
			},
			async : false,
			success : function(result) {
				if (result.success == "success") {
					var data = result.data;
					province = data;				
				}
			}
		});		
		$.ajax({
			url : path+'/organization/grid.json',
			data : {
				level : 3
			},
			async : false,
			success : function(result) {
				if (result.success == "success") {
					var data = result.data;
					city = data;				
				}
			}
		});		
		$.ajax({
			url : path+'/organization/grid.json',
			data : {
				level : 4
			},
			async : false,
			success : function(result) {
				if (result.success == "success") {
					var data = result.data;
					District = data;				
				}
			}
		});	 	 
		
		$("#selCompany").change(function () {
            var selValue = $(this).val(); 
            if (selValue == 0) {
               $("#selArea").empty();
   		 	   $("#selProvince").empty();
               $("#selCity").empty();
  		 	   $("#selDistrict").empty();
            } else {        
				$("#selArea").append("<option value='0'>请选择</option>");
	            $.each(area, function (k, p) { 
	                 if (p.parentId == selValue) {
	                     var option = "<option value='" + p.id + "'>" + p.name + "</option>";
	                     $("#selArea").append(option);
	                 }
	            });
            }
        });		
		$("#selArea").change(function () {
            var selValue = $(this).val();
            $("#selProvince").empty();
            $("#selCity").empty();
		 	$("#selDistrict").empty();
			$("#selProvince").append("<option value='0'>请选择</option>");
            $.each(province, function (k, p) { 
                 if (p.parentId == selValue) {
                     var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                     $("#selProvince").append(option);
                 }
             });
        }); 		
        $("#selProvince").change(function () {
              var selValue = $(this).val(); 
              $("#selCity").empty();
		 	  $("#selDistrict").empty();
			  $("#selCity").append("<option value='0'>请选择</option>");
              $.each(city, function (k, p) { 
                   if (p.parentId == selValue) {
                       var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                       $("#selCity").append(option);
                   }
               });
         });            
         $("#selCity").change(function () {
              var selValue = $(this).val();
              $("#selDistrict").empty(); 
			  $("#selDistrict").append("<option value='0'>请选择</option>");
              $.each(District, function (k, p) {
              if (p.parentId == selValue) {
                  var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                  $("#selDistrict").append(option);
               }
             }); 
         });
		 $("#selArea option:first").remove();
		 $("#selProvince option:first").remove();
		 $("#selCity option:first").remove();
		 $("#selDistrict option:first").remove();
		 
	});

function getOrganizationId() {
	var organizationId = 0;
	var level;
	if ($("#selDistrict").val() != 0 && $("#selDistrict").val() != null) {
 		organizationId = $("#selDistrict").val(); 
 		level = 5;
 	} else if ($("#selCity").val() != 0 && $("#selCity").val() != null) {
 		organizationId = $("#selCity").val();
 		level = 4;
 	} else if ($("#selProvince").val() != 0 && $("#selProvince").val() != null) {
 		organizationId = $("#selProvince").val(); 
 		level = 3;
 	} else if ($("#selArea").val() != 0 && $("#selArea").val() != null) {
 		organizationId = $("#selArea").val();
 		level = 2;
 	} else if ($("#selCompany").val() != 0 && $("#selCompany").val() != null) {
 		organizationId = $("#selCompany").val();
 		level = 1;
 	} 
	if (organizationId == 0) {
		alert('请选择上级机构!');
		return;
	}
	return organizationId + "|" + level;	
}
