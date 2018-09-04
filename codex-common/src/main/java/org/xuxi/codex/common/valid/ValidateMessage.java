package org.xuxi.codex.common.valid;

/**
 * jsr-349 使用,消息常量定义
 *
 */
@SuppressWarnings(value = "all")
public interface ValidateMessage {

    // 内容为空时的提示
    String NotBlank = "{com.changyi.valid.NotBlank.message}";
    // Url格式错误
    String URL = "{com.changyi.valid.URL.message}";
    // 邮件地址
    String Email = "{com.changyi.valid.Email.message}";
    // IP地址格式错误
    String Ip = "{com.changyi.valid.Ip.message}";
    // 交易类型错误
    String TradeType = "{com.changyi.valid.TradeType.message}";
    // 不为Empty
    String NotEmpty = "{com.changyi.valid.NotEmpty.message}";
    // 不为Null
    String NotNull = "{com.changyi.valid.NotNull.message}";
    // 为Null
    String Null = "{com.changyi.valid.Null.message}";

    String Past = "{com.changyi.valid.Past.message}";
    // 正则错误
    String Pattern = "{com.changyi.valid.Pattern.message}";
    // 对象长度判断错误
    String Size = "{com.changyi.valid.Size.message}";

    // 对象长度判断错误(固定)
    String fixedSize = "{com.changyi.valid.fixedSize.message}";

    // 字串长度判断错误
    String Length = "{com.changyi.valid.Length.message}";

    // 字串长度判断错误
    String MaxLength = "{com.changyi.valid.MaxLength.message}";

    // 字串长度判断错误
    String MinLength = "{com.changyi.valid.MinLength.message}";

    // 最小值错误
    String Min = "{com.changyi.valid.Min.message}";
    // 最大值错误
    String Max = "{com.changyi.valid.Max.message}";

    String Future = "{com.changyi.valid.Future.message}";

    String Digits = "{com.changyi.valid.Digits.message}";

    String AssertFalse = "{com.changyi.valid.AssertFalse.message}";

    String AssertTrue = "{com.changyi.valid.AssertTrue.message}";

    String DecimalMax ="{com.changyi.valid.DecimalMax.message}";

    String DecimalMin = "{com.changyi.valid.DecimalMin.message}";

}
