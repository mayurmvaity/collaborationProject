/**
 * 
 */

//for limiting characters

var i;
var divs = document.getElementsByTagName('div');
for(i=0;i<divs.length;i++) {
  if(divs[i].className == 'jobtext') {
    divs[i].innerHTML = divs[i].innerHTML.substring(0,200);
  }
}