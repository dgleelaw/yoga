$(function(){
	       'use strict';
	       $('.select2').select2({
	         minimumResultsForSearch: Infinity
	       });
	       $('.select2-show-search').select2({
	         minimumResultsForSearch: ''
	       });
	       $('#select2').select2({
	         dropdownCssClass: 'hover-success',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select3').select2({
	         dropdownCssClass: 'hover-danger',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select4').select2({
	         containerCssClass: 'select2-outline-success',
	         dropdownCssClass: 'bd-success hover-success',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select5').select2({
	         containerCssClass: 'select2-outline-info',
	         dropdownCssClass: 'bd-info hover-info',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select6').select2({
	         containerCssClass: 'select2-full-color select2-primary',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select7').select2({
	         containerCssClass: 'select2-full-color select2-danger',
	         dropdownCssClass: 'hover-danger',
	         minimumResultsForSearch: Infinity
	       });

	       // Full Colored Dropdown
	       $('#select8').select2({
	         dropdownCssClass: 'select2-drop-color select2-drop-primary',
	         minimumResultsForSearch: Infinity
	       });
	       $('#select9').select2({
	         dropdownCssClass: 'select2-drop-color select2-drop-indigo',
	         minimumResultsForSearch: Infinity
	       });

	       // Full colored for both box and dropdown
	       $('#select10').select2({
	         containerCssClass: 'select2-full-color select2-primary',
	         dropdownCssClass: 'select2-drop-color select2-drop-primary',
	         minimumResultsForSearch: Infinity
	       });

	       $('#select11').select2({
	         containerCssClass: 'select2-full-color select2-indigo',
	         dropdownCssClass: 'select2-drop-color select2-drop-indigo',
	         minimumResultsForSearch: Infinity 
	       });
	     });
