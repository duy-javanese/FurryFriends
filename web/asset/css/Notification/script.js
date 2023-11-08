/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var box  = document.getElementById('box');
var down = false;


function toggleNotifi(){
	if (down) {
		box.style.height  = '0px';
		box.style.opacity = 0;
		down = false;
	}else {
		box.style.height  = '510px';
		box.style.opacity = 1;
		down = true;
	}
}

