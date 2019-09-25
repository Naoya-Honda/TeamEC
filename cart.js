function checkValue(){
	var checkList = document.getElementsByClassName("checkList");
	var checkFlag = 0;
	for (  var i = 0;  i < checkList.length;  i++  ) {
		if(checkFlag == 0){
			if(checkList[i].checked) {
				checkFlag = 1;
				break;
			}
		}
	}
	if (checkFlag == 1) {
		document.getElementById('deleteButton').disabled="";
		document.getElementById('deleteButton').setAttribute("class","abled_button");
	} else {
		document.getElementById('deleteButton').disabled="true";
		document.getElementById('deleteButton').setAttribute("class","disabled_button");
	}
}
function goDeleteCartAction(){
	document.getElementById("cartForm").action="DeleteCartAction";
}