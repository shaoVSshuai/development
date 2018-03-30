// window.onload = function(){
//     var oBtn = document.getElementById('btn');
//     var oW,oLeft;
//     var oSlider=document.getElementById('slider');
//     var oTrack=document.getElementById('track');
//     var oIcon=document.getElementById('icon');
//     var oSpinner=document.getElementById('spinner');
//     var flag=1;
 
//     //绿色的宽度
//     var green = $("#slider").width() - 42; 
//     oBtn.addEventListener('touchstart',function(e){
//         console.log(flag);
//         if(flag==1){ 
//             console.log(e);
//             var touches = e.touches[0];
//             oW = touches.clientX - oBtn.offsetLeft;
//             oBtn.className="button";
//             oTrack.className="track"; 
//         }
        
//     },false);
 
//     oBtn.addEventListener("touchmove", function(e) {
//         if(flag==1){
//             var touches = e.touches[0];
//             oLeft = touches.clientX - oW;
//             if(oLeft < 0) {
//                 oLeft = 0;
//             }else if(oLeft > green) {
//                 oLeft =  green ;
//             }
//             oBtn.style.left = oLeft + "px";
//             oTrack.style.width=oLeft+ 'px';         
//         }
        
//     },false);
 
//     oBtn.addEventListener("touchend",function test() { 
//         if(oLeft>=(green)){
//             oBtn.style.left =  green;
//             oTrack.style.width= green;
//             oIcon.style.display='none';
//             oSpinner.style.display='block';             
//             flag=1;   
//             //oBtn.removeEventListener("touchend",test);
//             $("#phone").removeAttr('readonly');  
//             $(".send1").removeAttr('disabled');
//             $(".submit-btn").removeAttr('disabled'); 
//             $(".send1").addClass("send1-special");
//             $(".submit-btn").addClass("submit-btn-special");
//         }else{
//             oBtn.style.left = 0;
//             oTrack.style.width= 0;
//             flag = 1;
//         }
//         oBtn.className="button-on";
//         oTrack.className="track-on";       
//     },false);
//  }
