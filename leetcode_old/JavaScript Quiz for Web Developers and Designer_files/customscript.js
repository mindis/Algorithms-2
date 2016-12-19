var $ = jQuery.noConflict();
jQuery.fn.exists = function(callback) {
  var args = [].slice.call(arguments, 1);
  if (this.length) {
    callback.call(this, args);
  }
  return this;
};

/*----------------------------------------------------
/* Mark current day in calendar widget
/*--------------------------------------------------*/
jQuery(document).ready(function() {
	if ( jQuery('#calendar_wrap').length ) {
		jQuery('#calendar_wrap #today').each(function() {
			var $this = jQuery(this),
				dayIndex = $this.index();
			$this.closest('#wp-calendar').find('thead tr th').eq(dayIndex).addClass('today');
		});
	}
});

/*----------------------------------------------------
/* Scroll to top
/*--------------------------------------------------*/
jQuery(document).ready(function() {
	//move-to-top arrow
	jQuery("body").prepend("<div id='move-to-top' class='animate '><i class='fa fa-chevron-up'></i></div>");
	var scrollDes = 'html,body';  
	/*Opera does a strange thing if we use 'html' and 'body' together so my solution is to do the UA sniffing thing*/
	if(navigator.userAgent.match(/opera/i)){
		scrollDes = 'html';
	}
	//show ,hide
	jQuery(window).scroll(function () {
		if (jQuery(this).scrollTop() > 160) {
			jQuery('#move-to-top').addClass('filling').removeClass('hiding');
		} else {
			jQuery('#move-to-top').removeClass('filling').addClass('hiding');
		}
	});
	// scroll to top when click 
	jQuery('#move-to-top').click(function () {
		jQuery(scrollDes).animate({ 
			scrollTop: 0
		},{
			duration :500
		});
	});
});

/*----------------------------------------------------
/* Responsive Navigation
/*--------------------------------------------------*/
if (mts_customscript.responsive && mts_customscript.nav_menu != 'none') {
    jQuery(document).ready(function($){
        $('.secondary-navigation').append('<div id="mobile-menu-overlay" />');
        // merge if two menus exist
        if (!$('#navigation.mobile-only').length) {
            $('#navigation').not('.mobile-menu-wrapper').find('.menu').clone().appendTo('.mobile-menu-wrapper').hide();
        }
    
        $('.toggle-mobile-menu').click(function(e) {
            e.preventDefault();
            e.stopPropagation();
            $('body').toggleClass('mobile-menu-active');

            if ( $('body').hasClass('mobile-menu-active') ) {
                if ( $(document).height() > $(window).height() ) {
                    var scrollTop = ( $('html').scrollTop() ) ? $('html').scrollTop() : $('body').scrollTop();
                    $('html').addClass('noscroll').css( 'top', -scrollTop );
                }
                $('#mobile-menu-overlay').fadeIn();
            } else {
                var scrollTop = parseInt( $('html').css('top') );
                $('html').removeClass('noscroll');
                $('html,body').scrollTop( -scrollTop );
                $('#mobile-menu-overlay').fadeOut();
            }
        });
    }).on('click', function(event) {

        var $target = jQuery(event.target);
        if ( ( $target.hasClass("fa") && $target.parent().hasClass("toggle-caret") ) ||  $target.hasClass("toggle-caret") ) {// allow clicking on menu toggles
            return;
        }
        jQuery('body').removeClass('mobile-menu-active');
        jQuery('html').removeClass('noscroll');
        jQuery('#mobile-menu-overlay').fadeOut();
    });
}

/*----------------------------------------------------
/*  Dropdown menu
/* ------------------------------------------------- */
jQuery(document).ready(function($) {
    
    function mtsDropdownMenu() {
        var wWidth = $(window).width();
        if(wWidth > 865) {
            $('#navigation ul.sub-menu, #navigation ul.children').hide();
            var timer;
            var delay = 100;
            $('#navigation li').hover( 
              function() {
                var $this = $(this);
                timer = setTimeout(function() {
                    $this.children('ul.sub-menu, ul.children').slideDown('fast');
                }, delay);
                
              },
              function() {
                $(this).children('ul.sub-menu, ul.children').hide();
                clearTimeout(timer);
              }
            );
        } else {
            $('#navigation li').unbind('hover');
            $('#navigation li.active > ul.sub-menu, #navigation li.active > ul.children').show();
        }
    }

    mtsDropdownMenu();

    $(window).resize(function() {
        mtsDropdownMenu();
    });
});

/*---------------------------------------------------
/*  Vertical menus toggles
/* -------------------------------------------------*/
jQuery(document).ready(function($) {

    $('.widget_nav_menu, #navigation .menu').addClass('toggle-menu');
    $('.toggle-menu ul.sub-menu, .toggle-menu ul.children').addClass('toggle-submenu');
    $('.toggle-menu ul.sub-menu').parent().addClass('toggle-menu-item-parent');

    $('.toggle-menu .toggle-menu-item-parent').append('<span class="toggle-caret"><i class="fa fa-plus"></i></span>');

    $('.toggle-caret').click(function(e) {
        e.preventDefault();
        $(this).parent().toggleClass('active').children('.toggle-submenu').slideToggle('fast');
    });
});


/*----------------------------------------------------
/* Social button scripts
/*---------------------------------------------------*/
jQuery(document).ready(function(){
	jQuery.fn.exists = function(callback) {
	  var args = [].slice.call(arguments, 1);
	  if (this.length) {
		callback.call(this, args);
	  }
	  return this;
	};
	(function(d, s) {
	  var js, fjs = d.getElementsByTagName(s)[0], load = function(url, id) {
		if (d.getElementById(id)) {return;}
		js = d.createElement(s); js.src = url; js.id = id;
		fjs.parentNode.insertBefore(js, fjs);
	  };
	jQuery('span.facebookbtn, span.facebooksharebtn, .facebook_like').exists(function() {
	  load('//connect.facebook.net/en_US/all.js#xfbml=1', 'fbjssdk');
	});
	jQuery('span.gplusbtn').exists(function() {
	  load('https://apis.google.com/js/plusone.js', 'gplus1js');
	});
	jQuery('span.twitterbtn').exists(function() {
	  load('//platform.twitter.com/widgets.js', 'tweetjs');
	});
	jQuery('span.linkedinbtn').exists(function() {
	  load('//platform.linkedin.com/in.js', 'linkedinjs');
	});
	jQuery('span.pinbtn').exists(function() {
	  load('//assets.pinterest.com/js/pinit.js', 'pinterestjs');
	});
	jQuery('span.stumblebtn').exists(function() {
	  load('//platform.stumbleupon.com/1/widgets.js', 'stumbleuponjs');
	});
	}(document, 'script'));
});