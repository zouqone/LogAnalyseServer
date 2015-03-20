/**
 * 
 */

function initMoveHandle(movename,container){
	var MoveObj = {
			curTarget: null,
			curTmpTarget: null,
			noSel: function() {
				try {
					window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
				} catch(e){}
			},
			bindMouseDown: function(e) {
				var target = e.target;
				if(target.name!=movename){
					var divObj =  jQuery(target).parents('[name="move"]');
					if(divObj.length>0){
						target = divObj[0];
					}
				}
				target = jQuery(target);
				if (target!=null && target.attr('name')==movename) {
					var doc = $(document), target = $(target),
					docScrollTop = doc.scrollTop(),
					docScrollLeft = doc.scrollLeft();
					target.addClass("domBtn_Disabled");
					target.removeClass("domBtn");
					//curDom = $("<span class='dom_tmp domBtn'>" + target.text() + "</span>");
					
					curDom = target.clone();
					curDom.addClass('dom_tmp');
					curDom.appendTo("body");
					curDom.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
					
					MoveObj.curTarget = target;
					MoveObj.curTmpTarget = curDom;

					doc.bind("mousemove", MoveObj.bindMouseMove);
					doc.bind("mouseup", MoveObj.bindMouseUp);
					doc.bind("selectstart", MoveObj.docSelect);
				}
				if(e.preventDefault) {
					e.preventDefault();
				}
			},
			bindMouseMove: function(e) {
				MoveObj.noSel();
				var doc = $(document), 
				docScrollTop = doc.scrollTop(),
				docScrollLeft = doc.scrollLeft(),
				tmpTarget = MoveObj.curTmpTarget;
				if (tmpTarget) {
					tmpTarget.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
				}
				return false;
			},
			bindMouseUp : function(e){
				var doc = $(document);
				doc.unbind("mousemove", MoveObj.bindMouseMove);
				doc.unbind("mouseup", MoveObj.bindMouseUp);
				doc.unbind("selectstart", MoveObj.docSelect);

				var target = MoveObj.curTarget, tmpTarget = MoveObj.curTmpTarget;
				if (tmpTarget) tmpTarget.remove();

				var containerObj = e.target;
				if(containerObj!=null&&jQuery(containerObj).attr('name')!= container ){
					var containers = jQuery(containerObj).parents('[name="'+container+'"]');
					if(containers.length==1){
						containerObj = containers[0];
					}else{
						containerObj = null;
					}
				}
				
				if (containerObj!=null) {
					
					jQuery(containerObj).append(MoveObj.curTarget);
					
					if (target) {
						target.removeClass("domBtn_Disabled");
						target.addClass("domBtn");
					}
					MoveObj.curTarget = null;
					MoveObj.curTmpTarget = null;
				}
				/**/
				
			},
			bindDom: function() {
				$("[name='"+movename+"']").bind("mousedown", MoveObj.bindMouseDown);
			}
		}
	return MoveObj;
}
