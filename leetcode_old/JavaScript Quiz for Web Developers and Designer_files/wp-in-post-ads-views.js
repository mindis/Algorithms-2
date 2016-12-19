/**
 * Plugin Name: WP In Post Ads by MyThemeShop
 * Author URI: https://mythemeshop.com/
 */
(function( $ ) {

	'use strict';

	$(function() {

		var viewed_ids = '';

		if ( $('.wpipa-container').length ) {

			$('.wpipa-container').each(function() {

				var adID = $(this).data('id');

				if ( viewed_ids.indexOf( adID ) == -1 ) {

					viewed_ids = viewed_ids+','+adID;
				}
			});

			$.post( wpipaViews.url, {
				action: 'mts_ads_view_count',
				ids: viewed_ids
			});
		}
	});

})( jQuery );