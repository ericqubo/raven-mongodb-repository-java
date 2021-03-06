package raven.mongodb.repository;

import com.mongodb.MongoClient;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.PropertyCodecProvider;
import raven.mongodb.repository.conventions.CustomConventions;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


/**
 * @author yi.liang
 * @since JDK1.8
 */
public class PojoCodecRegistrys {

    /**
     * @param entityClazz
     * @return
     */
    public static CodecRegistry registry(final Class<?> entityClazz) {

        CodecRegistry pojoCodecRegistry = MongoClient.getDefaultCodecRegistry();
        //registry ValueEnum CodecProvider
        PropertyCodecProvider propertyCodecProvider = new raven.mongodb.repository.ValueEnumPropertyCodecProvider(pojoCodecRegistry);


        CodecRegistry res = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().conventions(CustomConventions.DEFAULT_CONVENTIONS).automatic(true).register(propertyCodecProvider).build()));

        //registry conventions
//        ClassModel<?> classModel = ClassModel.builder(entityClazz).conventions(CustomConventions.DEFAULT_CONVENTIONS).build();
//        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().register(classModel).register(propertyCodecProvider).build();
//        pojoCodecRegistry = CodecRegistries.fromRegistries(pojoCodecRegistry, CodecRegistries.fromProviders(pojoCodecProvider));

        return res;
    }
}
