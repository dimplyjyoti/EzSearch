<!DOCTYPE html>
<%@ page import="com.project.ezsearch.jsonclasses.SearchResponse"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="assets/images/search-862x744-69.png"
	type="image/x-icon">
<link rel="stylesheet"
	href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css" />
<meta name="description"
	content="Free Responsive Website Maker. Create awesome mobile-first websites. Easy and fast - No coding!">
<title>EzSearch</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/mobirise/css/style.css">
<link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css"
	type="text/css">

<!-- Paste any valid HTML code here. The code will be inserted to the end of <head> section, right before </head> -->
</head>
<body>

<%

String resultgeojsonStr = (String) request.getAttribute("resultGeojson");
Object mapBoundList = request.getAttribute("mapBoundList");

%>
	<section
		class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse"
		id="menu-0">
		<div class="mbr-navbar__section mbr-section">
			<div class="mbr-section__container container">
				<div class="mbr-navbar__container">
					<div
						class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
						<span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
							<span class="mbr-brand__logo"><img
								class="mbr-navbar__brand-img mbr-brand__img"
								src="assets/images/search-862x744-69.png"></span> <span
							class="mbr-brand__name"><a
								class="mbr-brand__name text-danger" href="home.jsp">EzSearch<br></a></span>
						</span>
					</div>
					<div class="mbr-navbar__hamburger mbr-hamburger text-white">
						<span class="mbr-hamburger__line"></span>
					</div>
					<div class="mbr-navbar__column mbr-navbar__menu">
						<nav
							class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
							<div class="mbr-navbar__column">
								<ul
									class="mbr-navbar__items mbr-navbar__items--right mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active mbr-buttons--only-links">
									<li class="mbr-navbar__item"><a
										class="mbr-buttons__link btn text-danger" href="home.jsp">HOME</a></li>
									<li class="mbr-navbar__item"><a
										class="mbr-buttons__link btn text-danger" href="about.jsp">ABOUT</a></li>
									<li class="mbr-navbar__item"><a
										class="mbr-buttons__link btn text-danger" href="contact.jsp">CONTACT</a></li>
								</ul>
							</div>

						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-after-navbar"
		id="form1-3" style="background-color: rgb(193, 193, 193);">

		<div
			class="mbr-section__container mbr-section__container--std-padding container">
			<div class="row">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-87 col-sm-offset-13">



<div id="status">
</div>

							<h2>Search Results:</h2>

							<%-- <%=((SearchResponse)request.getAttribute("resultJson")).getBusinesses().get(0).getName()%> --%>
							<%
								pageContext.setAttribute("businesses",
										((SearchResponse) request.getAttribute("resultJson")).getBusinesses());
							pageContext.setAttribute("namefromdb",
									((String) request.getAttribute("namefromdb")));
							%>

							++${pageScope.namefromdb}--
							<div class="col-xs-93 col-sm-92" id="map"></div>
							<div class="col-xs-94  col-sm-89">
								<ol type="1">
									<c:forEach var="element" items="${pageScope.businesses}">
										<p>
										<div class="col-sm-90">
											<img src="${element.image_url}" width="100" height="100">
										</div>

										<div class="col-sm-91">
											<li>${element.name}<br> Rating: ${element.rating} <br>
												Phone: ${element.display_phone} <br> Address:
												${element.location.display_address[0]},
												${element.location.display_address[1]}<br> Review:
												${element.snippet_text} <br></li>
										</div>


										</p>
									</c:forEach>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="footer1-2" style="background-color: rgb(68, 68, 68);">

		<div class="mbr-section__container container">
			<div class="mbr-footer mbr-footer--wysiwyg row">
				<div class="col-sm-12">
					<p class="mbr-footer__copyright">
						Copyright (c) 2015 Company Name. <a
							class="mbr-footer__link text-gray" href="terms.jsp">Terms of
							Use</a> | <a class="mbr-footer__link text-gray"
							href="https://mobirise.com/">Privacy Policy</a>
					</p>
				</div>
			</div>
		</div>
	</footer>


	<script src="assets/web/assets/jquery/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/smooth-scroll/SmoothScroll.js"></script>
	<!--[if lte IE 9]>
    <script src="assets/jquery-placeholder/jquery.placeholder.min.js"></script>
  <![endif]-->
	<script src="assets/mobirise/js/script.js"></script>
	<script src="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js"></script>
	<script>
		var map = L.map('map').setView([ 36.4551, -98.729 ], 10);
		L.tileLayer(
						'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',
						{
							maxZoom : 20,
							attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
									+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
									+ 'Imagery © <a href="http://mapbox.com">Mapbox</a>',
							id : 'mapbox.streets'
						}).addTo(map);

		map.fitBounds(<%=mapBoundList%>);

		L.geoJson((<%=resultgeojsonStr%>), {onEachFeature : onEachFeature}).addTo(map);
		var popup = L.popup();

		function onEachFeature(feature, layer) {
			// does this feature have a property named popupContent?
			if (feature.properties && feature.properties.popupContent) {
				layer.bindPopup(feature.properties.popupContent);
			}
		}
	</script>

	<!-- Paste any valid HTML code here. The code will be inserted to the end of <body> section, right before </body> -->
</body>
</html>