SELECT 
	STATION_CD,
	MIN(TO_NUMBER(CHUTE_NO)) CHUTE,
	COUNT(CHUTE_NO) CNT
FROM CHUTES
WHERE DOMAIN_ID = :domainId
  AND SORTER_CD = :sorterCd
  AND ACTIVE_FLAG = :activeFlag
  AND STATUS IS NULL
GROUP BY 
	STATION_CD
ORDER BY
	MIN(TO_NUMBER(CHUTE_NO))