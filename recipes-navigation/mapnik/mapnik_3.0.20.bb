SUMMARY = "Map tile renderer, mapnik, combines pixel-perfect image output with lightning-fast cartographic algorithms"
HOMEPAGE = "http://mapnik.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4b54a1fd55a448865a0b32d41598759d"

PR = "r0"

SRC_URI = "https://github.com/mapnik/mapnik/archive/v3.0.20.zip \
           file://0001-fix-SConstruct.patch \
	   file://0002-add-mapbox-variant-headers.patch \ 
"
SRC_URI[md5sum] = "1559c3551387b2b15eb0619f13755484"
SRC_URI[sha256sum] = "fb2072aedca1813c5e323afddb90c2ddd6518957aa2f52a00385c5c4962b310c"

DEPENDS += "postgis freetype icu libpng zlib boost apache2 python cairo harfbuzz"
DEPENDS += "\
	fontconfig \
	ttf-dejavu \
	ttf-droid \
"

inherit scons binconfig

# binconfig isn't smart enough to realize it should use directories
BINCONFIG_GLOB  = "mapnik-config/mapnik-config"

# these double quotes are magically awesome :)
EXTRA_OESCONS = " \
	CXX="${CXX}" \
	CC="${CC}" \
	CUSTOM_CXXFLAGS="${CXXFLAGS}" \
	CUSTOM_CFLAGS="${CFLAGS}" \
	CUSTOM_LDFLAGS="${LDFLAGS}" \
	DESTDIR=${D} \
	HOST=${TARGET_ARCH} \
	INPUT_PLUGINS=all \
	GDAL_CONFIG=${STAGING_BINDIR_CROSS}/gdal-config \
	PG_CONFIG=${STAGING_BINDIR_CROSS}/pg-config \
	BOOST_INCLUDES=${STAGING_INCDIR}/boost/ \
	BOOST_LIBS=${STAGING_LIBDIR} \
	CAIRO_INCLUDES=${STAGING_DIR_HOST}/usr/ \
	CAIRO_LIBS=${STAGING_LIBDIR} \
	FREETYPE_INCLUDES=${STAGING_INCDIR}/freetype2/ \
	FREETYPE_LIBS=${STAGING_LIBDIR} \
	HB_INCLUDES=${STAGING_INCDIR} \
	HB_LIBS=${STAGING_LIBDIR} \
	ICU_INCLUDES=${STAGING_INCDIR}/unicode/ \
	ICU_LIB_NAME=icuuc \
	ICU_LIBS=${STAGING_LIBDIR} \
	JPEG_INCLUDES=${STAGING_INCDIR} \
	JPEG_LIBS=${STAGING_LIBDIR} \
	OCCI_INCLUDES=${STAGING_INCDIR} \
	OCCI_LIBS=${STAGING_LIBDIR} \
	PG_INCLUDES=${STAGING_INCDIR}/postgresql/ \
	PG_LIBS=${STAGING_LIBDIR} \
	PNG_INCLUDES=${STAGING_INCDIR} \
	PNG_LIBS=${STAGING_LIBDIR} \
	PROJ_INCLUDES=${STAGING_INCDIR} \
	PROJ_LIBS=${STAGING_LIBDIR} \
	RASTERLITE_INCLUDES=${STAGING_INCDIR} \
	RASTERLITE_LIBS=${STAGING_LIBDIR} \
	SQLITE_INCLUDES=${STAGING_INCDIR} \
	SQLITE_LIBS=${STAGING_LIBDIR} \
	TIFF_INCLUDES=${STAGING_INCDIR} \
	TIFF_LIBS=${STAGING_LIBDIR} \
	WEBP_INCLUDES=${STAGING_INCDIR} \
	WEBP_LIBS=${STAGING_LIBDIR} \
	XML2_INCLUDES=${STAGING_INCDIR}/libxml2/ \
	XML2_LIBS=${STAGING_LIBDIR} \
"

do_install_append() {
	# this folder really belongs here
	mv ${D}/${includedir}/mapnik/mapbox ${D}/${includedir}/mapbox
}

sysroot_stage_all_append() {
  install -d ${SYSROOT_DESTDIR}${bindir}/crossscripts
  install -m 0755 ${D}${bindir}/mapnik-config ${SYSROOT_DESTDIR}${bindir}/crossscripts/mapnik-config
  sed -i -e 's:${STAGING_DIR}\/usr\/:"$( cd "$( dirname $( dirname "$0" ))" && pwd )":g' ${SYSROOT_DESTDIR}${bindir}/crossscripts/mapnik-config
}

FILES_${PN} += "${includedir}/mapbox"