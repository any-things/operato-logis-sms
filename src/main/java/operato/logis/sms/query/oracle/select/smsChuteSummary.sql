SELECT
	DOMAIN_ID
	, BATCH_ID
	, JOB_SEQ
	, JOB_DATE
    , SUB_EQUIP_CD CHUTE_NO
    , SHOP_CD
    , SHOP_NM
    , SUM(PICK_QTY) PICK_QTY
    , SUM(PICKED_QTY) PICKED_QTY
    , SUM(PICK_QTY) - SUM(PICKED_QTY) shortage
FROM
    JOB_INSTANCES
WHERE
BATCH_ID IN (SELECT ID FROM JOB_BATCHES WHERE EQUIP_TYPE = :equip_type AND JOB_DATE = :job_date AND DOMAIN_ID = :domainId)
GROUP BY
    DOMAIN_ID, JOB_SEQ, SUB_EQUIP_CD, SHOP_CD, SHOP_NM, BATCH_ID, JOB_DATE