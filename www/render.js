function render(d) {
    var c = document.getElementById("canvas")
    var ctx=c.getContext("2d")
    
    // clear
    ctx.fillStyle="#001020"
    ctx.fillRect(0,0,c.width,c.height)
    
    // fps
    ctx.strokeStyle = "#FFFFFF";
    ctx.lineWidth = 1;
    ctx.font = '14px san-serif';
    ctx.textBaseline = 'bottom';
    ctx.strokeText('FPS: ' + fps, 20, 40);
    
    // status
    ctx.strokeStyle = "#FFFF00";
    ctx.lineWidth = 1;
    ctx.font = '14px san-serif';
    ctx.textBaseline = 'bottom';
    ctx.strokeText(state.status, 80, 40);
    
    // surface
    ctx.strokeStyle="#003080"
    ctx.lineWidth = 6;
    ctx.beginPath();
    ctx.moveTo(0, c.height - 50);
    ctx.lineTo(c.width, c.height - 50);
    ctx.stroke();
    
    
    // draw sites
    for (var j = 0; j < state.sites.length; j++) {
        site = state.sites[j];
        
        if (site.type === "construction") {
        	ctx.strokeStyle="#AAAA00"
        	ctx.lineWidth = 3;
        	ctx.beginPath();
        	ctx.moveTo(site.x - posx, c.height - 52);
        	ctx.lineTo(site.x - posx + parseFloat(site.w), c.height - 52);
        	ctx.stroke();
        } else {        
        	ctx.drawImage(imgBuilding, site.x - posx, c.height - 170);
        }        
        
    }
    
    // draw platform
    for (var i = 0; i < state.platforms.length; i++) {
       platform = state.platforms[i];
   
       // avatar
       if (platform.type === 'capsule') {
           if (parseFloat(platform.y) != 0) {
               ctx.drawImage(imgDCapsule,platform.x - posx, c.height -90 - platform.y);
           } else {
               ctx.drawImage(imgCapsule,platform.x - posx, c.height -90 - platform.y);
           }           
           if (showLabels == true) {
    	   		ctx.strokeStyle = "#FFFF00";
    			ctx.lineWidth = 1;
    			ctx.font = '12px san-serif';
    			ctx.textBaseline = 'bottom';
    			ctx.strokeText(platform.label, platform.x - posx + 30, c.height - 80 - platform.y);
    	   }
           
       } else {
           if (platform.team == 1) {
               ctx.drawImage(imgRedTrux,platform.x - posx, c.height -90 - platform.y);
           } else if (platform.team == 2) {
               ctx.drawImage(imgBlueTrux,platform.x - posx, c.height - 90 - platform.y);
           } else {
               ctx.fillStyle="#FFFFFF"
               ctx.fillRect(platform.x - posx, c.height - 90 - platform.y, 40, 20)  
           }
           
    	   if (showLabels == true) {
    	   		ctx.strokeStyle = "#FFFF00";
    			ctx.lineWidth = 1;
    			ctx.font = '12px san-serif';
    			ctx.textBaseline = 'bottom';
    			ctx.strokeText(platform.label, platform.x - posx + 30, c.height - 80 - platform.y);
    	   }
    	   
       }
  
    }
    
}