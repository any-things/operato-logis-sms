package operato.logis.sms.query;

import org.springframework.stereotype.Component;

import xyz.anythings.sys.service.AbstractQueryStore;
import xyz.elidom.sys.SysConstants;

/**
 * 반품 프로세스
 * 
 * @author shortstop
 */
@Component
public class SmsQueryStore extends AbstractQueryStore {

	@Override
	public void initQueryStore(String databaseType) {
		this.databaseType = databaseType;
		this.basePath = "operato/logis/sms/query/" + this.databaseType + SysConstants.SLASH;
		this.defaultBasePath = "operato/logis/sms/query/ansi/"; 
	}
	
	/**
	 * BatchReceipt 조회
	 * 상세 Item 에 Order 타입이 있는 Case 
	 * @return
	 */
	public String getBatchReceiptOrderTypeStatusQuery() {
		return this.getQueryByPath("batch/BatchReceiptOrderTypeStatus");
	}
	
	/*** BatchReceipt 관련 데이터 쿼리 ***/
	/**
	 * WMS I/F 테이블로 부터 반품 BatchReceipt 데이터를 조회 한다.
	 * @return
	 */
	public String getWmsIfToReceiptDataQuery() {
		return this.getQueryByPath("batch/WmsIfToReceiptData");
	}
	
	/**
	 *WMS I/F 테이블로 부터  주문수신 완료된 데이터 변경('Y')
	 * 
	 * @return
	 */
	public String getWmsIfToReceiptUpdateQuery() {
		return this.getQueryByPath("batch/WmsIfToReceiptUpdate");
	}
	
}
