package com.benefire.framework.repository.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.benefire.framework.repository.annotation.Column;
import com.benefire.framework.repository.annotation.GeneratedValue;
import com.benefire.framework.repository.annotation.Id;
import com.benefire.framework.repository.annotation.Transient;
import com.benefire.framework.repository.id.GeneratorType;
import com.google.common.base.CaseFormat;

/**
 * @author jiang
 */
public class EntityPropertyHelper {

	public static List<EntityProperty> getEntityPropertys(Class<?> clazz,boolean isVerification) {
		Assert.notNull(clazz, "[Assertion failed] - this argument is required; it must not be null");
		List<EntityProperty> entityPropertys = new ArrayList<>();
		ReflectionUtils.doWithFields(clazz, field -> {
			try {
				if (null == AnnotationUtils.findAnnotation(field, Transient.class)) {
					String fieldName = field.getName();
					EntityProperty ep = new EntityProperty(fieldName, clazz);
					if(isVerification) {
						Assert.notNull(ep.getReadMethod(),String.join(" ",fieldName,"property not does not",
								       boolean.class.isAssignableFrom(ep.getPropertyType()) ? "is." : "gettr."));
						Assert.notNull(ep.getWriteMethod(),String.join(" ",fieldName,"property not does not settr Or settr param type and property mismatch."));
					}
					// column name
					Object columnName = AnnotationUtils.getValue(AnnotationUtils.findAnnotation(field, Column.class),"name");
					columnName = columnName == null ? CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName): columnName;
					ep.setColumnName(columnName.toString());
					// is primarykey
					if (null != AnnotationUtils.findAnnotation(field, Id.class))ep.setPrimarykey(true);
					// automatic data generation strategy
					GeneratedValue generated = AnnotationUtils.findAnnotation(field, GeneratedValue.class);
					if (null != generated) {
						ep.setGenerator(generated.generator());
					}
					entityPropertys.add(ep);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		});
		return entityPropertys;
	}

	public static class EntityProperty extends PropertyDescriptor {

		public EntityProperty(String propertyName, Class<?> beanClass) throws IntrospectionException {
			super(propertyName, beanClass);
		}

		// column name
		private String columnName;
		// is primary key
		private boolean primarykey;
		// automatic data generation strategy
		private GeneratorType generator;

		public String getColumnName() {

			return columnName;
		}

		private void setColumnName(String columnName) {

			this.columnName = columnName;
		}

		public boolean isPrimarykey() {

			return primarykey;
		}

		private void setPrimarykey(boolean primarykey) {

			this.primarykey = primarykey;
		}

		public GeneratorType getGenerator() {

			return generator;
		}

		private void setGenerator(GeneratorType generator) {

			this.generator = generator;
		}

		public Object getPropertyValue(Object object) {
			
			return ReflectionUtils.invokeMethod(this.getReadMethod(), object);
		}

		public void setPropertyValue(Object object,Object value) {
			
			ReflectionUtils.invokeMethod(this.getWriteMethod(), object,value);
		}
	}

}
