package com.piyushpriyadarshi.covid19mail.multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@EnableScheduling
public class AutoMailTrigger {
   private static final Logger LOGGER= LoggerFactory.getLogger(AutoMailTrigger.class);

    @Autowired
    ApplicationContext applicationContext;


    @Scheduled(cron = "${cron.expression}",zone = "")
    public void triggeMail(){
        LOGGER.info("Scheduled Mail triggering Start at "+new Date());
        try{
            Ap ap=applicationContext.getBean(Ap.class);
            Andaman andaman=applicationContext.getBean(Andaman.class);
            Bihar bihar=applicationContext.getBean(Bihar.class);
            Chandigarh chandigarh=applicationContext.getBean(Chandigarh.class);
            Chhattisgarh chhattisgarh=applicationContext.getBean(Chhattisgarh.class);
            Delhi delhi=applicationContext.getBean(Delhi.class);
            Goa goa=applicationContext.getBean(Goa.class);
            Gujarat gujarat=applicationContext.getBean(Gujarat.class);
            Hp hp=applicationContext.getBean(Hp.class);
            Jandk jandk=applicationContext.getBean(Jandk.class);
            Karnataka karnataka=applicationContext.getBean(Karnataka.class);
            Kerla kerla=applicationContext.getBean(Kerla.class);
            Ladakh ladakh=applicationContext.getBean(Ladakh.class);
            Mp mp=applicationContext.getBean(Mp.class);
            Maharashtra maharashtra=applicationContext.getBean(Maharashtra.class);
            Manipur manipur=applicationContext.getBean(Manipur.class);
            Mizoram mizoram=applicationContext.getBean(Mizoram.class);
            Odisha odisha=applicationContext.getBean(Odisha.class);
            Puducherry puducherry=applicationContext.getBean(Puducherry.class);
            Punjab punjab=applicationContext.getBean(Punjab.class);
            Rajasthan rajasthan=applicationContext.getBean(Rajasthan.class);
            TN tn=applicationContext.getBean(TN.class);
            Telengana telengana=applicationContext.getBean(Telengana.class);
            Uttarakhand uttarakhand=applicationContext.getBean(Uttarakhand.class);
            Up up=applicationContext.getBean(Up.class);
            WB wb=applicationContext.getBean(WB.class);
            Assam assam=applicationContext.getBean(Assam.class);
            Jharkhand jharkhand =applicationContext.getBean(Jharkhand.class);
            Arunachal arunachal=applicationContext.getBean(Arunachal.class);
            Tripura tripura=applicationContext.getBean(Tripura.class);
            Nagaland nagaland=applicationContext.getBean(Nagaland.class);
            Meghalaya meghalaya=applicationContext.getBean(Meghalaya.class);
            AllIndia allIndia=applicationContext.getBean(AllIndia.class);


            Thread t1=new Thread(ap);
            Thread t2=new Thread(andaman);
            Thread t3=new Thread(bihar);
            Thread t4=new Thread(chandigarh);
            Thread t5=new Thread(chhattisgarh);
            Thread t6=new Thread(delhi);
            Thread t7=new Thread(goa);
            Thread t8=new Thread(gujarat);
            Thread t9=new Thread(hp);
            Thread t10=new Thread(jandk);
            Thread t11=new Thread(karnataka);
            Thread t12=new Thread(kerla);
            Thread t13=new Thread(ladakh);
            Thread t14=new Thread(mp);
            Thread t15=new Thread(maharashtra);
            Thread t16=new Thread(manipur);
            Thread t17=new Thread(mizoram);
            Thread t18=new Thread(odisha);
            Thread t19=new Thread(puducherry);
            Thread t20=new Thread(punjab);
            Thread t21=new Thread(rajasthan);
            Thread t22=new Thread(tn);
            Thread t23=new Thread(telengana);
            Thread t24=new Thread(uttarakhand);
            Thread t25=new Thread(up);
            Thread t26=new Thread(wb);
            Thread t27=new Thread(assam);
            Thread t28=new Thread(jharkhand);
            Thread t29=new Thread(arunachal);
            Thread t30=new Thread(tripura);
            Thread t31=new Thread(nagaland);
            Thread t32=new Thread(meghalaya);
            Thread t33=new Thread(allIndia);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();
            t11.start();
            t12.start();
            t13.start();
            t14.start();
            t15.start();
            t16.start();
            t17.start();
            t18.start();
            t19.start();
            t20.start();
            t21.start();
            t22.start();
            t23.start();
            t24.start();
            t25.start();
            t26.start();
            t27.start();
            t28.start();
            t29.start();
            t30.start();
            t31.start();
            t32.start();
            t33.start();
        }
        catch (BeanCreationException e){
            LOGGER.warn("Issue While creating Bean "+e);
        }
        catch (Exception e){
            LOGGER.info("Issue While creating Bean "+e);
        }



    }
}
