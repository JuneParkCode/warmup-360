package org.kernel360.simpleboard.reply.controller;

import org.kernel360.simpleboard.common.crud.CRUDAbstractApiController;
import org.kernel360.simpleboard.reply.db.ReplyEntity;
import org.kernel360.simpleboard.reply.model.ReplyDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController extends CRUDAbstractApiController<ReplyDto, ReplyEntity> {
}
