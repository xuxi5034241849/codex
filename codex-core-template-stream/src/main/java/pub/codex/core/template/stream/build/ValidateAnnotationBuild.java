package pub.codex.core.template.stream.build;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pub.codex.common.Constant;

import java.util.List;
import java.util.Map;

/**
 * validate 的注解构建类
 *
 * @author xuxi
 */
@Component
public class ValidateAnnotationBuild {


    /**
     * {@NotBlank} 注解工厂方法
     *
     * @param annotation
     * @param groupMap
     * @return
     */
    public void NotBlankAnnotationFactory(List<String> annotation, Map<String, StringBuffer> groupMap) {
        this.baseAnnotationFactory(annotation, groupMap, Constant.annotationConatant.NOTBLANK);
    }

    /**
     * {@ApiModelProperty} 注解工厂方法
     *
     * @return
     */
    public void apiModelPropertyAnnotationFactory(List<String> annotation, Map<String, StringBuffer> groupMap, String comments) {

        StringBuffer groups = groupMap.get(Constant.annotationConatant.APIMODELPROPERTY.getValue());

        String annotationKey = Constant.annotationConatant.APIMODELPROPERTY.getValue();

        if (StringUtils.isNotBlank(comments) && StringUtils.isNotBlank(groups)) {
            annotation.add(annotationKey + "(describe = \"" + comments + "\",groups = {" + groups.deleteCharAt(groups.length() - 1) + "})");
        } else if (StringUtils.isNotBlank(comments)) {
            annotation.add(annotationKey + "(\"" + comments + "\")");
        } else if (StringUtils.isNotBlank(groups)) {
            annotation.add(annotationKey + "(groups = {" + groups.deleteCharAt(groups.length() - 1) + "})");
        }
    }

    /**
     * 基础 vaildate 注解拼装工厂方法
     *
     * @param annotation
     * @param groupMap
     * @param annoEnum
     * @return
     */
    public void baseAnnotationFactory(List<String> annotation, Map<String, StringBuffer> groupMap, Constant.annotationConatant annoEnum) {

        StringBuffer groups = groupMap.get(annoEnum.getValue());

        if (StringUtils.isNotBlank(groups)) {
            annotation.add(annoEnum.getValue() + "(groups = {" + groups.deleteCharAt(groups.length() - 1) + "})");
        }
    }

}