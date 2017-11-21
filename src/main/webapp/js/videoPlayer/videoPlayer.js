/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

  var player = document.getElementById('video');
    var volume_barra = document.getElementById('vol-control');
    var video_source = document.getElementById('video_source');
    volume_barra.value = (player.volume*100);
    var volume = player.volume;
    player.width = 560;
    function volumePlayer(){
        volume = player.volume;
    }
    function mute(){
        
        if(volume == 0 && player.volume == 0){
            //do nothing
        }else if(player.volume > 0){
            volumePlayer();
            player.volume = 0;
            volume_barra.value = (player.volume*100);
        }else if(player.volume == 0){
            player.volume = volume;
            volume_barra.value = (player.volume*100);
        }
    }
    function SetVolume(val){
        player.volume = val / 100;
        volumePlayer();
    }
    function playPause() { 
    if (player.paused) 
        player.play(); 
    else 
        player.pause(); 
    }
    function stop(){
        player.pause();
        player.currentTime = 0;
        player.load();
    }
    function changeVideo(caminho){
        player.pause();
        player.currentTime = 0;
        video_source.src = caminho;
        player.load();
        player.play();
    }
    function fullscreen(){
        if (player.requestFullscreen) {
            player.requestFullscreen();
        } else if (player.mozRequestFullScreen) {
            player.mozRequestFullScreen();
        } else if (player.webkitRequestFullscreen) {
            player.webkitRequestFullscreen();
        }
    }
