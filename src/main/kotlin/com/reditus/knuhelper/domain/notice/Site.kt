package com.reditus.knuhelper.domain.notice

enum class Site(val koreaName: String, val category: SiteCategory) {
    KOREAN("국어국문학과", SiteCategory.HUMANITIES),
    ENGLISH("영어영문학과", SiteCategory.HUMANITIES),
    HISTORY("사학과", SiteCategory.HUMANITIES),
    PHILOSOPHY("철학과", SiteCategory.HUMANITIES),
    FRENCH("불어불문학과", SiteCategory.HUMANITIES),
    GERMAN("독어독문학과", SiteCategory.HUMANITIES),
    ARAN("고고인류학과", SiteCategory.HUMANITIES),
    HANMUN("한문학과", SiteCategory.HUMANITIES),
    ////////////////////////////
    POLITICS("정치외교학과", SiteCategory.SOCIAL),
    SOCIO("사회학과", SiteCategory.SOCIAL),
    GEOG("지리학과", SiteCategory.SOCIAL),
    LIS("문헌정보학과", SiteCategory.SOCIAL),
    PSYCHOLOGY("심리학과", SiteCategory.SOCIAL),
    KNUSW("사회복지학부", SiteCategory.SOCIAL),
    MASSCOM("미디어커뮤니케이션학과", SiteCategory.SOCIAL),
    ////////////////////////////
    MATH("수학과", SiteCategory.NATURE),
    PHYSICS("물리학과", SiteCategory.NATURE),
    CHEM("화학과", SiteCategory.NATURE),
    STAT("통계학과", SiteCategory.NATURE),
    ////////////////////////////
    ECON("경제통상학부", SiteCategory.GYEONGSANG),
    KBIZ("경영학부", SiteCategory.GYEONGSANG),
    ////////////////////////////
    MATERIAL("신소재공학부", SiteCategory.ENGINEER),
    ME("기계공학부", SiteCategory.ENGINEER),
    ARCH("건축학부", SiteCategory.ENGINEER),
    CIVIL("토목공학과", SiteCategory.ENGINEER),
    APPCHEM("응용화학과", SiteCategory.ENGINEER),
    CHEME("화학공학과", SiteCategory.ENGINEER),
    KNUPOLYMER("고분자공학과", SiteCategory.ENGINEER),
    TEXTILENG("섬유시스템공학과", SiteCategory.ENGINEER),
    ENV("환경공학과", SiteCategory.ENGINEER),
    SOEE("에너지공학부", SiteCategory.ENGINEER),
    ////////////////////////////
    ELECTRONIC("전자공학부", SiteCategory.IT),
    SMOBILE("모바일공학전공", SiteCategory.IT),
    ELECTRIC("전기공학과", SiteCategory.IT),
    SEEAI("인공지능전공", SiteCategory.IT),
    CSE("컴퓨터학부", SiteCategory.IT),
    ////////////////////////////
    KAC("환경생명화학전공", SiteCategory.AGRICULTURE),
    PBS("식물생명화학전공", SiteCategory.AGRICULTURE),
    PLANTMED("식물의학과", SiteCategory.AGRICULTURE),
    FOODBIO("식품공학부", SiteCategory.AGRICULTURE),
    TREEI("임산공학전공", SiteCategory.AGRICULTURE),
    HORTI("원예과학과", SiteCategory.AGRICULTURE),
    BIOFIBER("바이오섬유소재학과", SiteCategory.AGRICULTURE),
    ACEN("농업토목공학전공", SiteCategory.AGRICULTURE),
    BIME("생물산업기계공학전공", SiteCategory.AGRICULTURE),
    KNUAGEC("식품자원경제학과", SiteCategory.AGRICULTURE),
    AIFM("농산업학과", SiteCategory.AGRICULTURE),
    ////////////////////////////
    MUSIC("음악학과", SiteCategory.ART),
    KMUSIC("국악학과", SiteCategory.ART),
    ARTS("미술학과", SiteCategory.ART),
    VCD("디자인학과", SiteCategory.ART),

    ////////////////////////////
    EDU("교육학과", SiteCategory.EDUCATION),
    KOREDU("국어교육과", SiteCategory.EDUCATION),
    ENGED("영어교육과", SiteCategory.EDUCATION),
    GEREDU("독어교육전공", SiteCategory.EDUCATION),
    FREDU("불어교육전공", SiteCategory.EDUCATION),
    HISEDU("역사교육과", SiteCategory.EDUCATION),
    GEOEDU("지리교육과", SiteCategory.EDUCATION),
    ILSA("일반사회교육과", SiteCategory.EDUCATION),
    ETHEDU("윤리교육과", SiteCategory.EDUCATION),
    MATHEDU("수학교육과", SiteCategory.EDUCATION),
    PHYSED("물리교육과", SiteCategory.EDUCATION),
    CHEDU("화학교육과", SiteCategory.EDUCATION),
    BIOEDU("생물교육과", SiteCategory.EDUCATION),
    EARTH("지구과학교육과", SiteCategory.EDUCATION),
    HOMEEDU("가정교육과", SiteCategory.EDUCATION),
    SPOEDU("체육교육과", SiteCategory.EDUCATION),
    ////////////////////////////
    VETERINARY("수의과대학", SiteCategory.VETERINARY),
    KNUCHILD("아동학부", SiteCategory.LIFESCIENCE),
    FASHION("의류학과", SiteCategory.LIFESCIENCE),
    FSNU("식품영양학과", SiteCategory.LIFESCIENCE),
    ////////////////////////////
    NURSE("간호학과", SiteCategory.NURSE),
    ////////////////////////////
    KNUPHARMACY("약학과", SiteCategory.PHARMACY),
    ////////////////////////////
    FORE("산림생태보호학과", SiteCategory.ECOLOGY),
    SCPLANT("식물자원학과", SiteCategory.ECOLOGY),
    ENTO("곤충생명과학과", SiteCategory.ECOLOGY),
    TOUR("관광학과", SiteCategory.ECOLOGY),
    SPORTS("체육학과", SiteCategory.ECOLOGY),
    ZOOTECHNY("축산학과", SiteCategory.ECOLOGY),
    ANIBIOTECH("동물생명공학과", SiteCategory.ECOLOGY),
    SPANIMAL("말/특수동물학과", SiteCategory.ECOLOGY),
    ////////////////////////////
    DISASTER("건선방재공학과", SiteCategory.SCIENCETECH),
    UE("환경안전공학과", SiteCategory.SCIENCETECH),
    PMEATKNU("정밀기계공학과", SiteCategory.SCIENCETECH),
    AUTOMOBILE("자동차공학부", SiteCategory.SCIENCETECH),
    SE("소프트웨어학과", SiteCategory.SCIENCETECH),
    CHEMENGSJC("에너지화학공학전공", SiteCategory.SCIENCETECH),
    FFSI("식품외식산업학과", SiteCategory.SCIENCETECH),
    TEXTILE("섬유공학전공", SiteCategory.SCIENCETECH),
    STYLE("패션디자인전공", SiteCategory.SCIENCETECH),
    LBIS("위치정보시스템학과", SiteCategory.SCIENCETECH),
    SMARTPLANT("스마트플랜트공학과", SiteCategory.SCIENCETECH),
    DEHY("치위생학과", SiteCategory.SCIENCETECH),
    ////////////////////////////
    PUAD("행정학부", SiteCategory.PUBLICADMIN),
    ////////////////////////////
    UDM("자율전공부", SiteCategory.AUTONOMOUS),
    ////////////////////////////
    KNU("경북대", SiteCategory.KNU),
    KNUJOB("진로취업과", SiteCategory.KNU),
    AIC("인공지능", SiteCategory.KNU),
    KNUCUBE("KNUCUBE", SiteCategory.KNU),
    GLOBAL("국제교류처", SiteCategory.KNU),
    ////////////////////////////
    SW("소프트웨어교육원", SiteCategory.SW);

    companion object{
        fun getSiteByKoreaName(koreaName: String): Site {
            return entries.find { it.koreaName == koreaName } ?: throw IllegalArgumentException("존재하지 않는 사이트입니다.")
        }
    }
}
