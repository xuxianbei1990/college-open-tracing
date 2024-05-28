package college.mysql.handler.after;

/**
 * 执行标记
 * User: EDY
 * Date: 2024/5/13
 * Time: 14:58
 * Version:V1.0
 */
public interface Handler<T> {

    void execute(T t);
}
