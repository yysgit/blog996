package com.yys.szcp.service.impl;

import com.yys.szcp.entity.TFundInfo;
import com.yys.szcp.mapper.FFundInfoMapper;
import com.yys.szcp.mapper.TFundInfoMapper;

import com.yys.szcp.service.FFundInfoService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class FFundInfoServiceImpl implements FFundInfoService {
    @Autowired
    private FFundInfoMapper fundInfoMapper;

    @Override
    public int addFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.addFundInfo(fundInfo);
    }


    @Override
    public int updateFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.updateFundInfo(fundInfo);
    }

    @Override
    public int deleteFundInfo(TFundInfo fundInfo) {
        return fundInfoMapper.deleteFundInfo(fundInfo);
    }

    @Override
    public List<Map> findFundInfoList(Map map) {
        return fundInfoMapper.findFundInfoList(map);
    }

    @Override
    public List<Map> findFundInfoInvestmentList(Map map) {

        List<Map> mapList = fundInfoMapper.findFundInfoInvestmentList(map);

        for (Map mapResult : mapList) {
            Map fundInfoParam = new HashMap();
            fundInfoParam.put("fundCode", mapResult.get("fund_code"));
            Map fundInfo = fundInfoMapper.findFundInfoByCode(fundInfoParam);


            //更新前净值和后净值


            Map fundDay = new HashMap();
            fundDay.put("fundCode", mapResult.get("fund_code"));
            fundDay.put("investmentAmount", "0");
            fundDay.put("nowHoldingTimes","");
            //投注
            try {
                if (mapResult.get("fund_net_worth1") != null) {
                    fundDay.put("fundDay", mapResult.get("fund_day1"));
                    if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) < Double.valueOf(String.valueOf(mapResult.get("behind_net_worth")))) {
                        if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1));
                            fundDay.put("investmentAmount", "+" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));
                        } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 3))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2));
                            fundDay.put("investmentAmount", "+" + (getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))) + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2), Integer.valueOf(String.valueOf(mapResult.get("fund_type"))))));

                        }

                    } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) > Double.valueOf(String.valueOf(mapResult.get("front_net_worth")))) {
                        if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1));
                        } else if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth1"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2));
                            fundDay.put("investmentAmount", "-" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));

                        }
                    } else {
                        fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))));
                    }

                    try {
                        fundInfoMapper.addFundDay(fundDay);
                    } catch (Exception e) {
                        System.out.printf(mapResult.get("fund_code") + "值已存在");
                    }

                    fundInfoMapper.updateFundDay(fundDay);
                }


                fundDay.put("investmentAmount", "0");
                fundDay.put("nowHoldingTimes","");
                if (mapResult.get("fund_net_worth2") != null) {
                    fundDay.put("fundDay", mapResult.get("fund_day2"));
                    if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) < Double.valueOf(String.valueOf(mapResult.get("behind_net_worth")))) {
                        if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1));
                            fundDay.put("investmentAmount", "+" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));
                        } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 3))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2));
                            fundDay.put("investmentAmount", "+" + (getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))) + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2), Integer.valueOf(String.valueOf(mapResult.get("fund_type"))))));

                        }

                    } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) > Double.valueOf(String.valueOf(mapResult.get("front_net_worth")))) {
                        if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1));
                        } else if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth2"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2));
                            fundDay.put("investmentAmount", "-" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));

                        }
                    } else {
                        fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))));

                    }

                    try {
                        fundInfoMapper.addFundDay(fundDay);
                    } catch (Exception e) {
                        System.out.printf(mapResult.get("fund_code") + "值已存在");
                    }

                    fundInfoMapper.updateFundDay(fundDay);
                }


                fundDay.put("investmentAmount", "0");
                fundDay.put("nowHoldingTimes","");
                if (mapResult.get("fund_net_worth3") != null) {
                    fundDay.put("fundDay", mapResult.get("fund_day3"));
                    if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) < Double.valueOf(String.valueOf(mapResult.get("behind_net_worth")))) {
                        if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1));
                            fundDay.put("investmentAmount", "+" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));
                        } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) >
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 3))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2));
                            fundDay.put("investmentAmount", "+" + (getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 1), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))) + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) + 2), Integer.valueOf(String.valueOf(mapResult.get("fund_type"))))));

                        }

                    } else if (Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) > Double.valueOf(String.valueOf(mapResult.get("front_net_worth")))) {
                        if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 1));
                        } else if ((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2) >= 0 && Double.valueOf(String.valueOf(mapResult.get("fund_net_worth3"))) <
                                Double.valueOf(String.valueOf(fundInfo.get("level" + (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2))))) {
                            fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times"))) - 2));
                            fundDay.put("investmentAmount", "-" + getInvestmentAmount((Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))), Integer.valueOf(String.valueOf(mapResult.get("fund_type")))));

                        }
                    } else {
                        fundDay.put("nowHoldingTimes", (Integer.valueOf(String.valueOf(mapResult.get("min_holding_times")))));
                    }

                    try {
                        fundInfoMapper.addFundDay(fundDay);
                    } catch (Exception e) {
                        System.out.printf(mapResult.get("fund_code") + "值已存在");
                    }

                    fundInfoMapper.updateFundDay(fundDay);
                }




            }catch (Exception e){
                e.printStackTrace();
            }

        }

        mapList = fundInfoMapper.findFundInfoInvestmentList(map);
        return mapList;
    }

    public Integer getInvestmentAmount(Integer num, Integer type) {
        if (type == 1) {
            switch (num) {
                case 1:
                    return 100;
                case 2:
                    return 200;
                case 3:
                    return 300;
                case 4:
                    return 400;
                case 5:
                    return 500;
                case 6:
                    return 600;
                case 7:
                    return 700;
                case 8:
                    return 800;
                case 9:
                    return 900;
                case 10:
                    return 1000;
                case 11:
                    return 1100;
                case 12:
                    return 1200;
                case 13:
                    return 1300;
                case 14:
                    return 1400;
                case 15:
                    return 1500;
                case 16:
                    return 1600;
                case 17:
                    return 1700;
                case 18:
                    return 1800;
                case 19:
                    return 1900;
                case 20:
                    return 2000;

            }
        } else {
            switch (num) {
                case 1:
                    return 20;
                case 2:
                    return 40;
                case 3:
                    return 60;
                case 4:
                    return 80;
                case 5:
                    return 100;
                case 6:
                    return 120;
                case 7:
                    return 140;
                case 8:
                    return 160;
                case 9:
                    return 180;
                case 10:
                    return 200;
                case 11:
                    return 220;
                case 12:
                    return 240;
                case 13:
                    return 260;
                case 14:
                    return 280;
                case 15:
                    return 300;
                case 16:
                    return 320;
                case 17:
                    return 340;
                case 18:
                    return 360;
                case 19:
                    return 380;
                case 20:
                    return 400;
            }
        }
        return 0;
    }
    public Integer getInvestmentAmountADD(Integer num, Integer type) {
        if (type == 1) {
            switch (num) {
                case 0:
                    return 2800;
                case 1:
                    return 2900;
                case 2:
                    return 3100;
                case 3:
                    return 3400;
                case 4:
                    return 3800;
                case 5:
                    return 4300;
                case 6:
                    return 5900;
                case 7:
                    return 5600;
                case 8:
                    return 6400;
                case 9:
                    return 7300;
                case 10:
                    return 8300;
                case 11:
                    return 9400;
                case 12:
                    return 10600;
                case 13:
                    return 11900;
                case 14:
                    return 13300;
                case 15:
                    return 14800;
                case 16:
                    return 16400;
                case 17:
                    return 18100;
                case 18:
                    return 19900;
                case 19:
                    return 21800;
                case 20:
                    return 23000;

            }
        } else {
            switch (num) {
                case 0:
                    return 200;
                case 1:
                    return 220;
                case 2:
                    return 260;
                case 3:
                    return 320;
                case 4:
                    return 400;
                case 5:
                    return 500;
                case 6:
                    return 620;
                case 7:
                    return 760;
                case 8:
                    return 920;
                case 9:
                    return 1100;
                case 10:
                    return 1300;
                case 11:
                    return 1520;
                case 12:
                    return 1760;
                case 13:
                    return 2020;
                case 14:
                    return 2300;
                case 15:
                    return 2600;
                case 16:
                    return 2920;
                case 17:
                    return 3260;
                case 18:
                    return 3620;
                case 19:
                    return 4000;
                case 20:
                    return 4400;
            }
        }
        return 0;
    }

    @Override
    public int addFundInfoList(String maxData, String maxDataDate, String fundCode, List<List<String>> fundValue) {
        Map fundInfoParam = new HashMap();
        fundInfoParam.put("fundCode", fundCode);


        //最大净值
        double maxNetWorth = Double.valueOf(maxData);
        fundInfoParam.put("maxNetWorth", maxNetWorth);
        long maxNetWorthDateLong = new Long(maxDataDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date maxNetWorthDate = new Date(maxNetWorthDateLong);
        fundInfoParam.put("maxNetWorthDate", simpleDateFormat.format(maxNetWorthDate));


        Map fundInfo = fundInfoMapper.findFundInfoByCode(fundInfoParam);

        List<List<String>> fundValue2 = new ArrayList<>();
        fundValue2.addAll(fundValue);


        for (int i = 0; i < 10; i++) {
            List<String> stringList = getMax(fundValue2);
            fundInfoParam.put("fundDay", simpleDateFormat.format(stringList.get(0)));
            fundInfoParam.put("fundNetWorth", stringList.get(1));
            System.out.println(simpleDateFormat.format(stringList.get(0)));
            try {
                fundInfoMapper.addFundNetWorth(fundInfoParam);

            } catch (Exception e) {
                System.out.printf(fundCode + "值已存在: " + fundInfoParam.get("fundDay") + " " + fundInfoParam.get("fundNetWorth"));
            }
            fundInfoMapper.updateFundNetWorth(fundInfoParam);
            fundValue2.remove(stringList);
        }

        //设置最大值
        int dueAmount= getInvestmentAmountADD(Integer.valueOf(String.valueOf(fundInfo.get("min_holding_times"))),Integer.valueOf(String.valueOf(fundInfo.get("fund_type"))));
        fundInfoParam.put("dueAmount",dueAmount);
        //更新基金信息
        Integer numUpdate = fundInfoMapper.updateFundInfoByCode(fundInfoParam);


        Map mapFundLevel = new HashMap();
        mapFundLevel.put("fundInfoCode", fundCode);
        double d = maxNetWorth * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level1", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level2", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level3", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level4", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level5", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level6", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level7", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level8", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level9", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level10", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level11", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level12", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level13", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level14", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level15", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level16", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level17", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level18", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level19", d);
        d = d * (1 - Double.valueOf(String.valueOf(fundInfo.get("volatility_value"))));
        mapFundLevel.put("level20", d);
        try {
            fundInfoMapper.addFundLevel(mapFundLevel);
        } catch (Exception e) {
            System.out.printf(fundCode + "值Level已存在");
        }
        fundInfoMapper.updateFundLevel(mapFundLevel);

        fundInfo = fundInfoMapper.findFundInfoByCode(fundInfoParam);

        double frontNetWorth = 0;
        double behindNetWorth = 0;

        if ((int) fundInfo.get("min_holding_times") == 1) {
            frontNetWorth = (double) fundInfo.get("level1");
            behindNetWorth = (double) fundInfo.get("level2");
        }
        if ((int) fundInfo.get("min_holding_times") == 2) {
            frontNetWorth = (double) fundInfo.get("level2");
            behindNetWorth = (double) fundInfo.get("level3");
        }
        if ((int) fundInfo.get("min_holding_times") == 3) {
            frontNetWorth = (double) fundInfo.get("level3");
            behindNetWorth = (double) fundInfo.get("level4");
        }
        if ((int) fundInfo.get("min_holding_times") == 4) {
            frontNetWorth = (double) fundInfo.get("level4");
            behindNetWorth = (double) fundInfo.get("level5");
        }
        if ((int) fundInfo.get("min_holding_times") == 5) {
            frontNetWorth = (double) fundInfo.get("level5");
            behindNetWorth = (double) fundInfo.get("level6");
        }
        if ((int) fundInfo.get("min_holding_times") == 6) {
            frontNetWorth = (double) fundInfo.get("level6");
            behindNetWorth = (double) fundInfo.get("level7");
        }
        if ((int) fundInfo.get("min_holding_times") == 7) {
            frontNetWorth = (double) fundInfo.get("level7");
            behindNetWorth = (double) fundInfo.get("level8");
        }
        if ((int) fundInfo.get("min_holding_times") == 8) {
            frontNetWorth = (double) fundInfo.get("level8");
            behindNetWorth = (double) fundInfo.get("level9");
        }
        if ((int) fundInfo.get("min_holding_times") == 9) {
            frontNetWorth = (double) fundInfo.get("level9");
            behindNetWorth = (double) fundInfo.get("level10");
        }
        if ((int) fundInfo.get("min_holding_times") == 10) {
            frontNetWorth = (double) fundInfo.get("level10");
            behindNetWorth = (double) fundInfo.get("level11");
        }
        if ((int) fundInfo.get("min_holding_times") == 11) {
            frontNetWorth = (double) fundInfo.get("level11");
            behindNetWorth = (double) fundInfo.get("level12");
        }
        if ((int) fundInfo.get("min_holding_times") == 12) {
            frontNetWorth = (double) fundInfo.get("level12");
            behindNetWorth = (double) fundInfo.get("level13");
        }
        if ((int) fundInfo.get("min_holding_times") == 13) {
            frontNetWorth = (double) fundInfo.get("level13");
            behindNetWorth = (double) fundInfo.get("level14");
        }
        if ((int) fundInfo.get("min_holding_times") == 14) {
            frontNetWorth = (double) fundInfo.get("level14");
            behindNetWorth = (double) fundInfo.get("level15");
        }
        if ((int) fundInfo.get("min_holding_times") == 15) {
            frontNetWorth = (double) fundInfo.get("level15");
            behindNetWorth = (double) fundInfo.get("level16");
        }
        if ((int) fundInfo.get("min_holding_times") == 16) {
            frontNetWorth = (double) fundInfo.get("level16");
            behindNetWorth = (double) fundInfo.get("level17");
        }
        if ((int) fundInfo.get("min_holding_times") == 17) {
            frontNetWorth = (double) fundInfo.get("level17");
            behindNetWorth = (double) fundInfo.get("level18");
        }
        if ((int) fundInfo.get("min_holding_times") == 18) {
            frontNetWorth = (double) fundInfo.get("level18");
            behindNetWorth = (double) fundInfo.get("level19");
        }
        if ((int) fundInfo.get("min_holding_times") == 19) {
            frontNetWorth = (double) fundInfo.get("level19");
            behindNetWorth = (double) fundInfo.get("level20");
        }
        if ((int) fundInfo.get("min_holding_times") == 20) {
            frontNetWorth = (double) fundInfo.get("level20");
            behindNetWorth = (double) fundInfo.get("level20");
        }

        fundInfoParam.put("frontNetWorth", frontNetWorth);
        fundInfoParam.put("behindNetWorth", behindNetWorth);
        fundInfoMapper.updateFundInfoNetWorthByCode(fundInfoParam);


        return 0;
    }


    private List<String> getMax(List<List<String>> fundValue) {

        List<String> stringList = fundValue.get(0);
        for (int i = 1; i < fundValue.size(); i++) {
            if (Double.valueOf(String.valueOf(fundValue.get(i).get(0))) > Double.valueOf(String.valueOf(stringList.get(0)))) {
                stringList = fundValue.get(i);
            }
        }
        return stringList;
    }

    ;

}
