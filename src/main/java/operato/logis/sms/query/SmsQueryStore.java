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
	
	/**
	 * 주문 데이터로 부터  주문 가공 쿼리
	 *
	 * @return
	 */
	public String getSdasGeneratePreprocessQuery(){
		return this.getQueryByPath("sdas/sdasGeneratePreprocess");
	}
	
	/**
	 * Station 시작 슈트번호
	 *
	 * @return
	 */
	public String getSdasStationQuery(){
		return this.getQueryByPath("sdas/sdasStation");
	}
	
	/**
	 * 작업 배치 별 주문 가공 정보에서 슈트별로 거래처 할당 상태를 조회 쿼리
	 *
	 * @return
	 */
	public String getSdasChuteStatusQuery() {
		return this.getQueryByPath("sdas/sdasChuteStatus");
	}
	
	/**
	 * 작업 배치 별 슈트별 물량 할당 요약 정보를 조회 쿼리
	 *
	 * @return
	 */
	public String getSdasPreprocessSummaryQuery() {
		return this.getQueryByPath("sdas/sdasPreprocessSummary");
	}
	
	/**
	 * 작업 생성
	 *
	 * @return
	 */
	public String getSdasGenerateJobInstancesQuery() {
		return this.getQueryByPath("sdas/sdasGenerateJobInstances");
	}
	
}
