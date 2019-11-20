module cloud.utils.aws.sdk2 {
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.http.urlconnection;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.services.ec2;
    requires software.amazon.awssdk.services.elasticloadbalancing;
    requires software.amazon.awssdk.services.iam;
    requires software.amazon.awssdk.services.s3;
    requires cloud.utils.aws.utils;
}