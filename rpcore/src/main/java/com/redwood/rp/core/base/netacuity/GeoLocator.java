package com.redwood.rp.core.base.netacuity;

import javax.inject.Named;

import net.digitalenvoy.netacuity.NaEdgeDb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.redwood.rp.core.annotation.Loggable;
import com.redwood.rp.security.vo.EdgeLocationVO;

//c:\netAcquity\NetAcuityAPI\java>mvn install:install-file -Dfile=c:\netAcquity\NetAcuityAPI\java\NetAcuity_API.jar -DgroupId=net.digitalenvoy.netacuity -DartifactId=netacuity -Dversion=0.0.1 -Dpackaging=jar

//67.222.106.50 66.135.210.61 4
@Named
public class GeoLocator {
	
	private static Logger logger = LoggerFactory.getLogger(GeoLocator.class.getName());

	
	@Value("${acpcore.netacuity.server.address}")
	private String serverIPAddress;


	public EdgeLocationVO getGeoLocation(String clientIPAddress) {
		NaEdgeDb edge = new NaEdgeDb();
		EdgeLocationVO edgeLocationVO=new EdgeLocationVO();
		try {
			edge.set_server_addr(serverIPAddress);
			edge.set_id(10);

			int ret = edge.naQueryEdge(clientIPAddress);
			if (ret == 0) {
				// logger.debug("Error in NaQueryEdge()!");
				// logger.debug("Edge Country: " + edge.edge_country);
				// logger.debug("Edge Region  = " + edge.edge_region) ;
				// logger.debug("Edge City  = " + edge.edge_city) ;
				// logger.debug("Edge Speed  = " +
				// edge.edge_connectionSpeed) ;
				edge.close();
				return null;
			}
			
			// logger.debug("Edge Country  = " + edge.edge_country) ;
			// logger.debug("Edge Region  = " + edge.edge_region) ;
			// logger.debug("Edge City  = " + edge.edge_city) ;
			// logger.debug("Edge Speed  = " + edge.edge_connectionSpeed);
			// logger.debug("Edge Metro Code = " + edge.edge_metro_code) ;
			// logger.debug("Edge Latitude   = " + edge.edge_latitude) ;
			// logger.debug("Edge Longitude  = " + edge.edge_longitude);
			// logger.debug("Edge Postal Code = " + edge.edge_postalCode);
			// logger.debug("Edge Country Code = " +
			// edge.edge_countryCode);
			// logger.debug("Edge Region Code = " + edge.edge_regionCode);
			// logger.debug("Edge City Code = " + edge.edge_cityCode);
			// logger.debug("Edge Continent Code = " +
			// edge.edge_continentCode);
			// logger.debug("Edge Two letter country = " +
			// edge.edge_twoLetterCountry);
			// logger.debug("Edge Area Codes = " + edge.edge_areaCodes);
			// logger.debug("Edge Country Conf = " +
			// edge.edge_countryConf);
			// logger.debug("Edge Region Conf = " + edge.edge_regionConf);
			// logger.debug("Edge City Conf = " + edge.edge_cityConf);
			// logger.debug("Edge Postal Code Conf = " +
			// edge.edge_postalCodeConf);
			// logger.debug("Edge GMT Offset = " + edge.edge_GMTOffset);
			// logger.debug("Edge In DST = " + edge.edge_inDST);
			
			
			edgeLocationVO.setEdgeCountry(edge.edge_country) ;
			edgeLocationVO.setEdgeRegion(edge.edge_region) ;
			edgeLocationVO.setEdgeCity(edge.edge_city) ;
			edgeLocationVO.setEdgeMetroCode(edge.edge_metro_code) ;
			edgeLocationVO.setEdgeLatitude(edge.edge_latitude) ;
			edgeLocationVO.setEdgeLongitude(edge.edge_longitude);
			edgeLocationVO.setEdgePostalCode(edge.edge_postalCode);
			edgeLocationVO.setEdgeCountryCode(edge.edge_countryCode);
			edgeLocationVO.setEdgeCityCode(edge.edge_cityCode);
			edgeLocationVO.setEdgeContinentCode(edge.edge_continentCode);
			edgeLocationVO.setEdgeTwoLetterCountry(edge.edge_twoLetterCountry);
			edgeLocationVO.setEdgeAreaCodes(edge.edge_areaCodes);
			edgeLocationVO.setEdgeGMTOffset(edge.edge_GMTOffset);
			edgeLocationVO.setEdgeInDST(edge.edge_inDST);
			

			edge.close();
			return edgeLocationVO;

			
		} catch (Exception ex) {
			logger.error("Couldn't process the clientIP", ex);
			return null;
		}finally{
			edge.close();
		}
	}
}
