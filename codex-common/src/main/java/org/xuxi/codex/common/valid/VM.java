package org.xuxi.codex.common.valid;

/**
 * jsr-349 使用,消息常量定义
 *
 */
@SuppressWarnings(value = "all")
public interface VM {

    // 内容为空时的提示
    String NotBlank = "{valid.NotBlank.message}";
    // Url格式错误
    String URL = "{valid.URL.message}";
    // 邮件地址
    String Email = "{valid.Email.message}";
    // IP地址格式错误
    String Ip = "{valid.Ip.message}";
    // 交易类型错误
    String TradeType = "{valid.TradeType.message}";
    // 不为Empty
    String NotEmpty = "{valid.NotEmpty.message}";
    // 不为Null
    String NotNull = "{valid.NotNull.message}";
    // 为Null
    String Null = "{valid.Null.message}";

    String Past = "{valid.Past.message}";
    // 正则错误
    String Pattern = "{valid.Pattern.message}";
    // 对象长度判断错误
    String Size = "{valid.Size.message}";

    // 对象长度判断错误(固定)
    String fixedSize = "{valid.fixedSize.message}";

    // 字串长度判断错误
    String Length = "{valid.Length.message}";

    // 字串长度判断错误
    String MaxLength = "{valid.MaxLength.message}";

    // 字串长度判断错误
    String MinLength = "{valid.MinLength.message}";

    // 最小值错误
    String Min = "{valid.Min.message}";
    // 最大值错误
    String Max = "{valid.Max.message}";

    String Future = "{valid.Future.message}";

    String Digits = "{valid.Digits.message}";

    String AssertFalse = "{valid.AssertFalse.message}";

    String AssertTrue = "{valid.AssertTrue.message}";

    String DecimalMax ="{valid.DecimalMax.message}";

    String DecimalMin = "{valid.DecimalMin.message}";

}
