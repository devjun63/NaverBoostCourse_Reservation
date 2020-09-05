var image_ul = document.querySelector(".visual_img");

	window.onload = function() {
		var imgCnt = 0;
		/* Animation: sliding setting */
		image_ul.querySelectorAll("li").forEach(()=> {
			imgCnt ++;
		});
		image_ul.style.width = (image_ul.offsetWidth * imgCnt) + "px";
		
		//console.log(imgCnt);
		//console.log(image_ul.style.width);
		slideShow(imgCnt);
	}

	/* Animation: sliding */
	function slideShow(imgCnt) {
		var curIndex = 0;
		
		setInterval( () => {
			image_ul.style.transition = "transform 2s ease-out";
			image_ul.style.transform = "translate3d(-" + 414*(curIndex+1)+"px, 0px, 0px)";
			curIndex++;
			
			//console.log(curIndex);
			if( curIndex === imgCnt-1 ) {
				curIndex = -1;
			}
		},2000);	
	}